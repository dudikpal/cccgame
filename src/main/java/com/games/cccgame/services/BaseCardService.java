package com.games.cccgame.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.commands.*;
;
import com.games.cccgame.dtos.BaseCardDTO;
import com.games.cccgame.helper.Calculate;
import com.games.cccgame.models.*;
import com.games.cccgame.repositories.BaseCardRepository;
import com.games.cccgame.repositories.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@AllArgsConstructor
public class BaseCardService {

    private BaseCardRepository baseCardRepository;

    private PlayerCardRepository playerCardRepository;

    private ModelMapper modelMapper;

    private Calculate calculate;

    private MongoTemplate mongoTemplate;

    private final String REGEX_IS_NUMBER = "^\\d+(\\.\\d+)?$";

    public List<BaseCardDTO> getFilteredBaseCards(BaseCardFilterCommand command) {

        if (command.filterIsEmptyOrNull()) {
            return getAllBaseCards();
        }

        BaseCardFilter filter = modelMapper.map(command, BaseCardFilter.class);
        List<BaseCardDTO> filteredBaseCards = new ArrayList <>();
        Query query = new Query();
        List<Criteria> criterias = new ArrayList <>();

        criterias.addAll(simpleCriterias(filter.getSimpleValues()));
        criterias.addAll(multipleCriterias(filter.getMultipleValues()));
        criterias.addAll(betweenCriterias(filter.getBetweens()));
        query.addCriteria(new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()])));
        filteredBaseCards.addAll(
            Arrays.stream(mongoTemplate.find(query, BaseCard.class).toArray())
                .map(baseCard -> modelMapper.map(baseCard, BaseCardDTO.class))
                .toList()
        );

        return filteredBaseCards;
    }

    public List<BaseCardDTO> getAllBaseCards() {
        return baseCardRepository.findAll().stream()
            .map(baseCard -> modelMapper.map(baseCard, BaseCardDTO.class))
            .toList();
    }

    private List<Criteria> betweenCriterias(Between[] betweens) {
        List<Criteria> criterias = new ArrayList <>();

        for (Between between : betweens) {
            String attributeName = between.getName();
            criterias.add(Criteria.where(attributeName).gte(parseToNumber(between.getMin())).lte(parseToNumber(between.getMax())));
        }

        return criterias;
    }

    private List<Criteria> multipleCriterias(MultipleValue[] multipleValues) {

        List<Criteria> criterias = new ArrayList <>();

        for (MultipleValue multipleValue : multipleValues) {
            String attributeName = multipleValue.getName();

            for (String value : multipleValue.getValues()) {
                criterias.add(criteriaWithParsedValue(attributeName, value));
            }
        }

        return criterias;
    }

    private List<Criteria> simpleCriterias(SimpleValue[] values) {

        List<Criteria> criterias = new ArrayList <>();

        for (SimpleValue simpleValue : values) {
            String attributeName = simpleValue.getName();
            String value = simpleValue.getValue();
            criterias.add(criteriaWithParsedValue(attributeName, value));
        }

        return criterias;
    }

    private Criteria criteriaWithParsedValue(String attributeName, String value) {
        String regexContainValue = "(?i).*" + value + ".*";

        return Criteria.where(attributeName).regex(regexContainValue);
    }

    private boolean isNumber(String str) {
        Pattern p = Pattern.compile(REGEX_IS_NUMBER);
        Matcher m = p.matcher(str);

        return m.find();
    }

    private Number parseToNumber(String value) {
        if (value.contains(".")) {
            return Float.parseFloat(value);
        }

        return Integer.parseInt(value);
    }

    private boolean filterIsEmpty(BaseCardFilter filter) {

        return Arrays.stream(filter.getSimpleValues()).count() == 0
            && Arrays.stream(filter.getMultipleValues()).count() == 0
            && Arrays.stream(filter.getBetweens()).count() == 0;
    }

    public BaseCardDTO getBaseCardById(String id) {
        BaseCard baseCard = baseCardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("BaseCard dont exists with this id: " + id));
        baseCardRepository.save(baseCard);

        return modelMapper.map(baseCard, BaseCardDTO.class);
    }

    public BaseCardDTO createBaseCard(CreateBaseCardCommand command) {
        BaseCard baseCard = modelMapper.map(command, BaseCard.class);
        baseCardRepository.save(baseCard);

        return modelMapper.map(baseCard, BaseCardDTO.class);
    }

    public BaseCardDTO updateBaseCard(UpdateBaseCardCommand command) {
        BaseCard baseCard = modelMapper.map(command, BaseCard.class);
        baseCardRepository.save(baseCard);
        updateInPlayerCards(baseCard);

        return modelMapper.map(baseCard, BaseCardDTO.class);
    }

    private void updateInPlayerCards(BaseCard baseCard) {
        List<PlayerCard> playerCards = playerCardRepository.findAllByBaseCardId(baseCard.getId());
        for (PlayerCard playerCard : playerCards) {
            playerCard.setBaseCard(baseCard);
            calculate.playerCardFields(playerCard);
            playerCardRepository.save(playerCard);
        }
        log.info("Update baseCard in all playercards is finished.");
    }

    public void deleteBaseCard(DeleteBaseCardCommand command) {
        baseCardRepository.deleteById(command.getId());
        log.info(command.getId() + " baseCard deleted successfully");
    }

    public void bulkCreateBaseCards(List<CreateBaseCardCommand> commands) {
        for (CreateBaseCardCommand command : commands) {
            createBaseCard(command);
        }
    }

    public void dropBaseCardsTable() {
        baseCardRepository.deleteAll();
    }

    public BaseCardDTO getBaseCardSkeleton() {
        return new BaseCardDTO();
    }

    public void bulkUpdateBaseCard(List<UpdateBaseCardCommand> commands) {
        for (UpdateBaseCardCommand singleUpdateCommand : commands) {
            updateBaseCard(singleUpdateCommand);
        }
    }
}
