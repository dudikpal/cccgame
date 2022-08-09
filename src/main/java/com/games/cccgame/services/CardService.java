package com.games.cccgame.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.CreateCardCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.FindCardsParams;
import com.games.cccgame.mapper.DTOMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CardService {

    private CardRepository cardRepository;

    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;

    private MongoTemplate mongoTemplate;

    public List <CardDTO> getAllCards() {

        /*String cards = "[{\"id\":\"c_0-20v-154hp-7278\",\"manufacturer\":\"Fiat\",\"type\":\"Coupe 2.0 20V\",\"year\":1993,\"country\":\"N/A\",\"doors\":2,\"body\":\"Coupe\",\"seats\":4,\"driveWheel\":\"FWD\",\"fuelType\":\"Petrol\",\"fuelTankCapacity\":63,\"engineCapacity\":1998,\"powerKW\":114,\"powerHP\":154,\"maxTorque\":186,\"topSpeed\":215,\"acceleration\":8.4,\"weight\":1270,\"length\":4250,\"width\":1768,\"height\":1355,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/fiat-coupe-fa-175-2.0-20v-154hp-7278.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.auto-data.net/en/fiat-coupe-fa-175-2.0-20v-154hp-7278\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_0-tsi-evo-300hp-dsg-42357\",\"manufacturer\":\"Cupra\",\"type\":\"Leon 2.0 TSI EVO\",\"year\":2021,\"country\":\"N/A\",\"doors\":5,\"body\":\"Hatchback\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"Petrol\",\"fuelTankCapacity\":50,\"engineCapacity\":1984,\"powerKW\":223,\"powerHP\":300,\"maxTorque\":400,\"topSpeed\":250,\"acceleration\":5.7,\"weight\":1490,\"length\":4398,\"width\":1799,\"height\":1442,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/cupra-leon-2.0-tsi-evo-300hp-dsg-42357.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.auto-data.net/en/cupra-leon-2.0-tsi-evo-300hp-dsg-42357\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_2-i-turbo-231hp-8302\",\"manufacturer\":\"Lotus\",\"type\":\"Esprit 2.2 i Turbo\",\"year\":1976,\"country\":\"N/A\",\"doors\":2,\"body\":\"Coupe\",\"seats\":2,\"driveWheel\":\"RWD\",\"fuelType\":\"Petrol\",\"fuelTankCapacity\":73,\"engineCapacity\":2174,\"powerKW\":172,\"powerHP\":231,\"maxTorque\":295,\"topSpeed\":250,\"acceleration\":5.5,\"weight\":1270,\"length\":4330,\"width\":1860,\"height\":1150,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/lotus-esprit-2.2-i-turbo-231hp-8302.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.auto-data.net/en/lotus-esprit-2.2-i-turbo-231hp-8302\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_0-v6-150hp-11650\",\"manufacturer\":\"Rover\",\"type\":\"75 2.0 V6\",\"year\":2001,\"country\":\"N/A\",\"doors\":5,\"body\":\"Estate\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"Petrol\",\"fuelTankCapacity\":65,\"engineCapacity\":1997,\"powerKW\":111,\"powerHP\":150,\"maxTorque\":185,\"topSpeed\":201,\"acceleration\":10.2,\"weight\":1505,\"length\":4792,\"width\":1778,\"height\":1424,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/rover-75-tourer-2.0-v6-150hp-11650.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.auto-data.net/en/rover-75-tourer-2.0-v6-150hp-11650\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},\n" +
                "{\"id\":\"c_lincoln-continental_59792\",\"manufacturer\":\"Lincoln\",\"type\":\"Continental\",\"year\":1995,\"country\":\"N/A\",\"doors\":4,\"body\":\"Sedan\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":67,\"engineCapacity\":4603,\"powerKW\":194,\"powerHP\":264,\"maxTorque\":359,\"topSpeed\":-1,\"acceleration\":-1.0,\"weight\":1760,\"length\":5240,\"width\":1870,\"height\":1420,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/lincoln-continental_59792.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/lincoln-continental-specs/59792\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":2.77,\"gear2nd\":1.54,\"gear3rd\":1.0,\"gear4th\":0.69,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":3.56},{\"id\":\"c_mercury-sable-stationwagon-ls_59813\",\"manufacturer\":\"Mercury\",\"type\":\"Sable Stationwagon LS\",\"year\":1992,\"country\":\"N/A\",\"doors\":5,\"body\":\"Estate\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":61,\"engineCapacity\":3797,\"powerKW\":104,\"powerHP\":141,\"maxTorque\":292,\"topSpeed\":180,\"acceleration\":-1.0,\"weight\":1580,\"length\":4910,\"width\":1810,\"height\":1400,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/mercury-sable-stationwagon-ls_59813.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/mercury-sable-stationwagon-ls-specs/59813\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":2.77,\"gear2nd\":1.54,\"gear3rd\":1.0,\"gear4th\":0.69,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":3.37},{\"id\":\"c_suzuki-swift-1-5-gls_47270\",\"manufacturer\":\"Suzuki\",\"type\":\"Swift 1.5 GLS\",\"year\":2005,\"country\":\"N/A\",\"doors\":5,\"body\":\"Hatchback\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":45,\"engineCapacity\":1490,\"powerKW\":75,\"powerHP\":102,\"maxTorque\":133,\"topSpeed\":185,\"acceleration\":10.0,\"weight\":975,\"length\":3695,\"width\":1690,\"height\":1500,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/suzuki-swift-1-5-gls_47270.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/suzuki-swift-1-5-gls-specs/47270\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":3.55,\"gear2nd\":1.9,\"gear3rd\":1.31,\"gear4th\":0.97,\"gear5th\":0.77,\"gear6th\":-1.0,\"finalDrive\":4.11},{\"id\":\"c_audi-a8-55-tfsi-quattro_80368\",\"manufacturer\":\"Audi\",\"type\":\"A8 55 TFSI quattro\",\"year\":2017,\"country\":\"N/A\",\"doors\":4,\"body\":\"Sedan\",\"seats\":5,\"driveWheel\":\"4WD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":72,\"engineCapacity\":2995,\"powerKW\":250,\"powerHP\":340,\"maxTorque\":500,\"topSpeed\":250,\"acceleration\":5.6,\"weight\":1895,\"length\":5172,\"width\":1945,\"height\":1473,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"yes\",\"imageUrl\":\"assets/img/cars/audi-a8-55-tfsi-quattro_80368.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/audi-a8-55-tfsi-quattro-specs/80368\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":4.71,\"gear2nd\":3.14,\"gear3rd\":2.11,\"gear4th\":1.67,\"gear5th\":1.29,\"gear6th\":1.0,\"finalDrive\":3.08},{\"id\":\"c_suzuki-liana-1-6-glx_47096\",\"manufacturer\":\"Suzuki\",\"type\":\"Liana 1.6 GLX\",\"year\":2002,\"country\":\"N/A\",\"doors\":4,\"body\":\"Sedan\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":50,\"engineCapacity\":1586,\"powerKW\":76,\"powerHP\":103,\"maxTorque\":144,\"topSpeed\":170,\"acceleration\":-1.0,\"weight\":1110,\"length\":4350,\"width\":1690,\"height\":1545,\"groundClearance\":160,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/suzuki-liana-1-6-glx_47096.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/suzuki-liana-1-6-glx-specs/47096\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":3.55,\"gear2nd\":1.9,\"gear3rd\":1.31,\"gear4th\":0.97,\"gear5th\":0.82,\"gear6th\":-1.0,\"finalDrive\":4.11},{\"id\":\"c_aston-martin-vantage_79941\",\"manufacturer\":\"Aston Martin\",\"type\":\"Vantage\",\"year\":2018,\"country\":\"N/A\",\"doors\":2,\"body\":\"Coupe\",\"seats\":2,\"driveWheel\":\"RWD\",\"fuelType\":\"gasoline\",\"fuelTankCapacity\":73,\"engineCapacity\":4000,\"powerKW\":375,\"powerHP\":510,\"maxTorque\":685,\"topSpeed\":314,\"acceleration\":3.6,\"weight\":1530,\"length\":4465,\"width\":1942,\"height\":1273,\"groundClearance\":-1,\"abs\":\"yes\",\"tractionControl\":\"yes\",\"imageUrl\":\"assets/img/cars/aston-martin-vantage_79941.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.cars-data.com/en/aston-martin-vantage-specs/79941\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":4.71,\"gear2nd\":3.14,\"gear3rd\":2.11,\"gear4th\":1.67,\"gear5th\":1.28,\"gear6th\":1.0,\"finalDrive\":2.93},\n" +
                "{\"id\":\"c_1992_buick_park-avenue_36\",\"manufacturer\":\"Buick\",\"type\":\"Park Avenue Ultra V6 Supercharger\",\"year\":1992,\"country\":\"N/A\",\"doors\":-1,\"body\":\"Sedan\",\"seats\":-1,\"driveWheel\":\"FWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":68,\"engineCapacity\":3800,\"powerKW\":152,\"powerHP\":205,\"maxTorque\":352,\"topSpeed\":-1,\"acceleration\":8.41,\"weight\":1651,\"length\":5207,\"width\":1854,\"height\":1397,\"groundClearance\":127,\"abs\":\"N/A\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/1992_buick_park-avenue_36.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/1992/buick/park-avenue/36\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_2007_audi_a4_71215\",\"manufacturer\":\"Audi\",\"type\":\"A4 2.0T\",\"year\":2007,\"country\":\"N/A\",\"doors\":4,\"body\":\"Sedan\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":70,\"engineCapacity\":2000,\"powerKW\":149,\"powerHP\":200,\"maxTorque\":280,\"topSpeed\":-1,\"acceleration\":7.58,\"weight\":1544,\"length\":4572,\"width\":1752,\"height\":1422,\"groundClearance\":101,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/2007_audi_a4_71215.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/2007/audi/a4/71215\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":3.67,\"gear2nd\":2.05,\"gear3rd\":1.37,\"gear4th\":1.03,\"gear5th\":0.8,\"gear6th\":0.66,\"finalDrive\":3.75},{\"id\":\"c_2014_dodge_avenger_35821\",\"manufacturer\":\"Dodge\",\"type\":\"Avenger V6\",\"year\":2014,\"country\":\"N/A\",\"doors\":-1,\"body\":\"Sedan\",\"seats\":-1,\"driveWheel\":\"FWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":63,\"engineCapacity\":3600,\"powerKW\":211,\"powerHP\":283,\"maxTorque\":352,\"topSpeed\":-1,\"acceleration\":6.56,\"weight\":1636,\"length\":4876,\"width\":1828,\"height\":1473,\"groundClearance\":152,\"abs\":\"yes\",\"tractionControl\":\"yes\",\"imageUrl\":\"assets/img/cars/2014_dodge_avenger_35821.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/2014/dodge/avenger/35821\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_1991_volvo_940_36963\",\"manufacturer\":\"Volvo\",\"type\":\"940 SE Turbo\",\"year\":1991,\"country\":\"N/A\",\"doors\":-1,\"body\":\"Sedan\",\"seats\":-1,\"driveWheel\":\"RWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":79,\"engineCapacity\":2300,\"powerKW\":120,\"powerHP\":162,\"maxTorque\":264,\"topSpeed\":-1,\"acceleration\":8.62,\"weight\":1499,\"length\":4851,\"width\":1752,\"height\":1397,\"groundClearance\":101,\"abs\":\"N/A\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/1991_volvo_940_36963.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/1991/volvo/940/36963\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_1999_oldsmobile_aurora_7802\",\"manufacturer\":\"Oldsmobile\",\"type\":\"Aurora V8\",\"year\":1999,\"country\":\"N/A\",\"doors\":4,\"body\":\"Sedan\",\"seats\":5,\"driveWheel\":\"FWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":70,\"engineCapacity\":4000,\"powerKW\":186,\"powerHP\":250,\"maxTorque\":352,\"topSpeed\":-1,\"acceleration\":7.63,\"weight\":1770,\"length\":5207,\"width\":1879,\"height\":1397,\"groundClearance\":127,\"abs\":\"yes\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/1999_oldsmobile_aurora_7802.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/1999/oldsmobile/aurora/7802\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":2.96,\"gear2nd\":1.63,\"gear3rd\":1.0,\"gear4th\":0.68,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":3.48},{\"id\":\"c_2015_bentley_continental-gt3-r_44722\",\"manufacturer\":\"Bentley\",\"type\":\"Continental GT3-R V8 Twin turbo\",\"year\":2015,\"country\":\"N/A\",\"doors\":-1,\"body\":\"Coupe\",\"seats\":-1,\"driveWheel\":\"4WD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":90,\"engineCapacity\":4000,\"powerKW\":426,\"powerHP\":572,\"maxTorque\":702,\"topSpeed\":-1,\"acceleration\":4.27,\"weight\":2194,\"length\":4800,\"width\":1930,\"height\":1397,\"groundClearance\":-25,\"abs\":\"N/A\",\"tractionControl\":\"yes\",\"imageUrl\":\"assets/img/cars/2015_bentley_continental-gt3-r_44722.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/2015/bentley/continental-gt3-r/44722\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0},{\"id\":\"c_2003_aston-martin_db7-vantage_12032\",\"manufacturer\":\"Aston Martin\",\"type\":\"DB7 Vantage Volante\",\"year\":2003,\"country\":\"N/A\",\"doors\":-1,\"body\":\"Convertible\",\"seats\":-1,\"driveWheel\":\"RWD\",\"fuelType\":\"N/A\",\"fuelTankCapacity\":81,\"engineCapacity\":-1000,\"powerKW\":313,\"powerHP\":420,\"maxTorque\":-1,\"topSpeed\":-1,\"acceleration\":4.62,\"weight\":1860,\"length\":-25,\"width\":-25,\"height\":-25,\"groundClearance\":-25,\"abs\":\"N/A\",\"tractionControl\":\"N/A\",\"imageUrl\":\"assets/img/cars/2003_aston-martin_db7-vantage_12032.webp\",\"logoURL\":\"assets/img/placeholder.png\",\"carPageUrl\":\"https://www.carspecs.us/cars/2003/aston-martin/db7-vantage/12032\",\"objectPositionHorizontal\":\"center\",\"objectPositionVertical\":\"\",\"objectWidth\":\"100%\",\"objectHeight\":\"100%\",\"gear1st\":-1.0,\"gear2nd\":-1.0,\"gear3rd\":-1.0,\"gear4th\":-1.0,\"gear5th\":-1.0,\"gear6th\":-1.0,\"finalDrive\":-1.0}]\n";*/
        List <Card> cards = cardRepository.findAll();


        return cards.stream()
            .map(card -> modelMapper.map(card, CardDTO.class))
            .toList();
    }

    public CardDTO createCard(CreateCardCommand command) {

        Card card = new Card();

        if (!cardRepository.existsById(command.getId())) {

            card = modelMapper.map(command, Card.class);
            cardRepository.save(card);
        }

        return DTOMapper.CardToCardDTO(card);
    }

    @Transactional
    public CardDTO updateCard(UpdateCardCommand command) {

        Card card = cardRepository.findById(command.getId())
            .orElseThrow(() -> new IllegalArgumentException("Cannot fond card with this id: " + command.getId()));

        cardRepository.save(card);

        return DTOMapper.CardToCardDTO(card);
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

            List <Card> cardList = mapper.readValue(cardsJson, new TypeReference <List <Card>>() {
            });

            for (Card card : cardList) {

                if (cardRepository.findById(card.getId()).isEmpty()) {

                    CreateCardCommand createCardCommand = modelMapper.map(card, CreateCardCommand.class);
                    cardDTOs.add(createCard(createCardCommand));
                } else {

                    UpdateCardCommand updateCardCommand = modelMapper.map(card, UpdateCardCommand.class);
                    cardDTOs.add(updateCard(updateCardCommand));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardDTOs;
    }


    public List <CardDTO> findCards(String command) {

        FindCardsParams findParams = stringToParams(command);

        if (!findParamsIsEmpty(findParams)) {

            JsonNode simpleValues = findParams.getSimpleValues();
            List <String> checkedFieldNames = findParams.getCheckedFieldNames();
            JsonNode betweens = findParams.getBetweens();
            JsonNode multipleValues = findParams.getMultipleValues();

            Query query = new Query();
            List <CardDTO> filteredCards = new ArrayList <>();
            List <Criteria> criterias = new ArrayList <>();

            criterias.addAll(simpleCriterias(simpleValues));
            criterias.addAll(isNullCriterias(checkedFieldNames));
            criterias.addAll(multipleCriterias(multipleValues));
            criterias.addAll(betweensCriterias(betweens));

            query.addCriteria(new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()])));

            filteredCards.addAll(Arrays.stream(mongoTemplate.find(query, Card.class).toArray())
                .map(c -> DTOMapper.CardToCardDTO((Card) c))
                .collect(Collectors.toList()));

            return filteredCards;
        }


        return getAllCards();
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


    public void removeAllCard() {

        cardRepository.deleteAll();
    }
}
