package com.games.cccgame.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.DataDTO;
import com.games.cccgame.dtos.FindCardsParams;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repository.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerCardService {

    private CardService cardService;

    private PlayerCardRepository playerCardRepository;

    private ObjectMapper objectMapper;

    private MongoTemplate mongoTemplate;

    @Autowired
    private PlayerCardMapper playerCardMapper;

    public PlayerCardDTO getPlayerCard(String playerCardId) {

        PlayerCard playerCard = playerCardRepository.findById(playerCardId).get();
        PlayerCardDTO playerCardDTO = playerCardMapper.playerCardToDTO(playerCard);

        return playerCardDTO;
    }


    public PlayerCardDTO createPlayerCard(String cardId) {

        Card card = cardService.getCard(cardId);
        PlayerCard playerCard = playerCardRepository
            .save(new PlayerCard(card, LocalDate.now()));

        return playerCardMapper.playerCardToDTO(playerCard);
    }


    public List<PlayerCardDTO> getPlayerCardsToAdmin(String command) {

        List<PlayerCardDTO> playerCardDTOS = findCardsByCriterias(command);

        return playerCardDTOS;
    }


    public List <PlayerCardDTO> findCardsByCriterias(String command) {

        List <CardDTO> filteredCards = cardService.findCardsByCriterias(command);
        List <PlayerCardDTO> filteredPlayerCards = filteredCards.stream()
            .map(cardDTO -> {
                PlayerCard playerCard = new PlayerCard();
                playerCard.setCard(new Card());
                PlayerCardDTO playerCardDTO = playerCardMapper.playerCardToDTO(playerCard);
                playerCardDTO.setCard(new DataDTO("Card", cardDTO));
                return playerCardDTO;
            })
            .toList();

        return filteredPlayerCards;
    }


    private List <Criteria> betweensCriterias(JsonNode betweensValues) {

        List <Criteria> criterias = new ArrayList <>();

        for (JsonNode node : betweensValues) {

            String attrName = node.get("name").asText();
            List <String> values = jsonToStringArray(node);
            Number valueFrom = convertStringToNumber(values.get(0));
            Number valueTo = convertStringToNumber(values.get(1));

            criterias.add(Criteria.where(attrName).gte(valueFrom).lte(valueTo));
        }

        return criterias;
    }


    private List <Criteria> multipleCriterias(JsonNode multipleValues) {

        List <Criteria> criterias = new ArrayList <>();

        for (JsonNode node : multipleValues) {

            String attrName = node.get("name").asText();
            List <String> values = jsonToStringArray(node);

            for (String value : values) {

                criterias.add(createCriteriaWithParsedValue(attrName, value.trim()));
            }
        }

        return criterias;
    }


    private List <String> jsonToStringArray(JsonNode node) {

        List <String> list = new ArrayList <>();

        for (JsonNode objNode : node.get("values")) {

            list.add(objNode.asText());
        }

        return list;
    }


    private List <Criteria> isNullCriterias(List <String> fieldNames) {

        List <Criteria> criterias = new ArrayList <>();

        for (String fieldName : fieldNames) {

            criterias.add(new Criteria().orOperator(
                Criteria.where(fieldName).regex("N/A"),
                Criteria.where(fieldName).lt(0)
            ));
        }

        return criterias;
    }


    private List <Criteria> simpleCriterias(JsonNode simpleValues) {

        List <Criteria> criterias = new ArrayList <>();

        for (JsonNode node : simpleValues) {
            String attrName = node.get("name").asText();

            try {
                String value = objectMapper.readTree(node.get("values").toPrettyString()).get(0).asText().trim();

                criterias.add(createCriteriaWithParsedValue(attrName, value));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return criterias;
    }


    private Criteria createCriteriaWithParsedValue(String attrName, String value) {

        String regex = "";

        if (value.chars().allMatch(Character::isDigit) || value.contains(".")) {

            return Criteria.where(attrName).is(convertStringToNumber(value));

        } else {

            regex = "(?i).*" + value + ".*";
            return Criteria.where(attrName).regex(regex);
        }
    }


    private Number convertStringToNumber(String value) {

        if (value.contains(".")) {

            return (Number) Double.parseDouble(value);

        } else {

            return (Number) Integer.parseInt(value);
        }
    }


    private boolean findParamsIsEmpty(FindCardsParams params) {

        return params.getCheckedFieldNames().size() == 0
            && params.getBetweens().size() == 0
            && params.getMultipleValues().size() == 0
            && params.getSimpleValues().size() == 0;
    }


    private FindCardsParams stringToParams(String command) {

        String checks = "";
        JsonNode json;
        JsonNode betweens = null;
        JsonNode simpleValues = null;
        JsonNode multipleValues = null;
        List <String> checkedFieldNames = null;
        FindCardsParams result = new FindCardsParams();

        try {
            json = objectMapper.readTree(command);
            checks = json.get("checks").toPrettyString();
            betweens = objectMapper.readTree(json.get("betweens").toPrettyString());
            multipleValues = objectMapper.readTree(json.get("multipleValues").toPrettyString());
            simpleValues = objectMapper.readTree(json.get("simpleValues").toPrettyString());
            checkedFieldNames = extractCheckedFieldNames(checks);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        result.setChecks(checks);
        result.setBetweens(betweens);
        result.setMultipleValues(multipleValues);
        result.setSimpleValues(simpleValues);
        result.setCheckedFieldNames(checkedFieldNames);

        return result;
    }


    private List <String> extractCheckedFieldNames(String checks) {

        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(checks);
        List <String> checkedFields = new ArrayList <>();

        while (m.find()) {

            checkedFields.add(m.group());
        }

        return checkedFields;
    }

    public PlayerCardDTO getPlayerCardSkeleton() {

        CardDTO cardDTO = new CardDTO();
        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
        playerCardDTO.setCard(new DataDTO("Card", cardDTO));

        return playerCardDTO;
    }
}
