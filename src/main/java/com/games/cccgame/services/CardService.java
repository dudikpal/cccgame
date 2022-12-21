package com.games.cccgame.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.CreateCardCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.FindCardsParams;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CardService {

    private CardRepository cardRepository;

    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;

    @Autowired
    private CardMapper cardMapper;

    private MongoTemplate mongoTemplate;

    public List <CardDTO> getCards() {

        return cardRepository.findAll().stream()
            .map(card -> cardMapper.CardToCardDTO(card))
            .toList();
    }

    public Card getCard(String cardId) {

        return cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("Cannot find card with this ID"));
    }

    public CardDTO createCard(CreateCardCommand command) {

        Card card = new Card();

        if (!cardRepository.existsById(command.getId())) {

            card = modelMapper.map(command, Card.class);
            calculateCardCornering(card);
            cardRepository.save(card);
        }

        return cardMapper.CardToCardDTO(card);
    }

    @Transactional
    public Card updateCard(UpdateCardCommand command) {

        cardRepository.findById(command.getId().getValue().toString())
            .orElseThrow(() -> new IllegalArgumentException("Cannot find card with this id: " + command.getId().getValue().toString()));
        Card updatedCard = cardMapper.CardDTOToCard(modelMapper.map(command, CardDTO.class));
        calculateCardCornering(updatedCard);
        cardRepository.save(updatedCard);

        return updatedCard;
    }


    private Card calculateCardCornering(Card card) {
        // be kéne hozni vhogy a weightet is
        double msToKmh = 3.6;
        double gravity = 9.81;
        int cornerRadiusMeter = 20;
        double widthMeter = (double)card.getWidth() / 1000;
        double heightMeter = (double)card.getHeight() / 1000;
        int cornering = (int)(Math.sqrt((cornerRadiusMeter * gravity * (widthMeter / 2)) / (heightMeter / 4)) * msToKmh);
        card.setCornering(cornering);

        return card;
    }


    public void removeCard(String id) {

        Card card = cardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find card with id: " + id));

        cardRepository.delete(card);
    }


    public List <CardDTO> uploadCardsFromFile(String cardsJson) {

        ObjectMapper mapper = new ObjectMapper();
        List <CardDTO> cardDTOs = new ArrayList <>();

        try {

            List <Card> cardList = mapper.readValue(cardsJson, new TypeReference <>() {
            });

            for (Card card : cardList) {

                //if (cardRepository.findById(card.getId()).isEmpty()) {

                    //String createCardCommand = card.toString();
                    CreateCardCommand createCardCommand = modelMapper.map(card, CreateCardCommand.class);
                    cardDTOs.add(createCard(createCardCommand));
                /*} else {
                    // a már létezőket felülírja
                    UpdateCardCommand updateCardCommand = modelMapper.map(card, UpdateCardCommand.class);
                    cardDTOs.add(updateCard(updateCardCommand));
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardDTOs;
    }


    public List <CardDTO> findCardsByCriterias(String command) {

        FindCardsParams findParams = stringToParams(command);
        List <CardDTO> filteredCards = new ArrayList <>();

        if (!findParamsIsEmpty(findParams)) {

            JsonNode simpleValues = findParams.getSimpleValues();
            List <String> checkedFieldNames = findParams.getCheckedFieldNames();
            JsonNode betweens = findParams.getBetweens();
            JsonNode multipleValues = findParams.getMultipleValues();

            Query query = new Query();
            List <Criteria> criterias = new ArrayList <>();

            criterias.addAll(simpleCriterias(simpleValues));
            criterias.addAll(isNullCriterias(checkedFieldNames));
            criterias.addAll(multipleCriterias(multipleValues));
            criterias.addAll(betweensCriterias(betweens));

            query.addCriteria(new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()])));

            filteredCards.addAll(Arrays.stream(mongoTemplate.find(query, Card.class).toArray())
                //.map(c -> playerCardMapper.playerCardToDTO(new PlayerCard((Card) c, LocalDate.now())))
                .map(c -> cardMapper.CardToCardDTO((Card) c))
                .collect(Collectors.toList()));

            /*for (Object cardObject : Arrays.stream(mongoTemplate.find(query, Card.class).toArray()).toList()) {
                Card card = (Card) cardObject;
                PlayerCard playerCard = new PlayerCard(card, LocalDate.now());
                filteredCards.add(playerCardMapper.playerCardToDTO(playerCard));
            }*/

        System.out.println(objectMapper.valueToTree(filteredCards));
            return filteredCards;
        }

        for (CardDTO cardDTO : getCards()) {

            //PlayerCard playerCard = new PlayerCard(cardMapper.CardDTOToCard(cardDTO), LocalDate.now());

            //filteredCards.add(playerCardMapper.playerCardToDTO(playerCard));
        }
        filteredCards = getCards();
        return filteredCards;
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
        System.out.println("jsonnode incoming parameter");
        System.out.println(multipleValues);
        for (JsonNode node : multipleValues) {

            String attrName = node.get("name").asText();
            List <String> values = jsonToStringArray(node);

            for (String value : values) {
                System.out.println(attrName+ ": " + value);
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
        //return getCards();
    }


    public void removeAllCard() {

        cardRepository.deleteAll();
    }
}
