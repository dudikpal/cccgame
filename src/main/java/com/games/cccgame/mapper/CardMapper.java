package com.games.cccgame.mapper;

import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.models.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public Card CardDTOToCard(CardDTO cardDTO) {

        Card card = new Card();

        card.setId((String) cardDTO.getId().getValue());
        card.setManufacturer((String) cardDTO.getManufacturer().getValue());
        card.setType((String) cardDTO.getType().getValue());
        card.setYear(Integer.parseInt(cardDTO.getYear().getValue().toString()));
        card.setCountry((String) cardDTO.getCountry().getValue());
        card.setCornering(Integer.parseInt(cardDTO.getCornering().getValue().toString()));
        card.setDoors(Integer.parseInt(cardDTO.getDoors().getValue().toString()));
        card.setBody((String) cardDTO.getBody().getValue());
        card.setSeats(Integer.parseInt(cardDTO.getSeats().getValue().toString()));
        card.setDriveWheel((String) cardDTO.getDriveWheel().getValue());
        card.setFuelType((String) cardDTO.getFuelType().getValue());
        card.setFuelTankCapacity(Integer.parseInt(cardDTO.getFuelTankCapacity().getValue().toString()));
        card.setEngineCapacity(Integer.parseInt(cardDTO.getEngineCapacity().getValue().toString()));
        card.setPowerKW(Integer.parseInt(cardDTO.getPowerKW().getValue().toString()));
        card.setPowerHP(Integer.parseInt(cardDTO.getPowerHP().getValue().toString()));
        card.setMaxTorque(Integer.parseInt(cardDTO.getMaxTorque().getValue().toString()));
        card.setTopSpeed(Integer.parseInt(cardDTO.getTopSpeed().getValue().toString()));
        card.setAcceleration(Double.parseDouble(cardDTO.getAcceleration().getValue().toString()));
        card.setWeight(Integer.parseInt(cardDTO.getWeight().getValue().toString()));
        card.setLength(Integer.parseInt(cardDTO.getLength().getValue().toString()));
        card.setWidth(Integer.parseInt(cardDTO.getWidth().getValue().toString()));
        card.setHeight(Integer.parseInt(cardDTO.getHeight().getValue().toString()));
        card.setGroundClearance(Integer.parseInt(cardDTO.getGroundClearance().getValue().toString()));
        card.setAbs((String) cardDTO.getAbs().getValue());
        card.setTractionControl((String) cardDTO.getTractionControl().getValue());
        card.setImageUrl((String) cardDTO.getImageUrl().getValue());
        card.setLogoURL((String) cardDTO.getLogoURL().getValue());
        card.setCarPageUrl((String) cardDTO.getCarPageUrl().getValue());
        card.setObjectPositionHorizontal((String) cardDTO.getObjectPositionHorizontal().getValue());
        card.setObjectPositionVertical((String) cardDTO.getObjectPositionVertical().getValue());
        card.setObjectWidth((String) cardDTO.getObjectWidth().getValue());
        card.setObjectHeight((String) cardDTO.getObjectHeight().getValue());
        card.setGear1st(Double.parseDouble(cardDTO.getGear1st().getValue().toString()));
        card.setGear2nd(Double.parseDouble(cardDTO.getGear2nd().getValue().toString()));
        card.setGear3rd(Double.parseDouble(cardDTO.getGear3rd().getValue().toString()));
        card.setGear4th(Double.parseDouble(cardDTO.getGear4th().getValue().toString()));
        card.setGear5th(Double.parseDouble(cardDTO.getGear5th().getValue().toString()));
        card.setGear6th(Double.parseDouble(cardDTO.getGear6th().getValue().toString()));
        card.setFinalDrive(Double.parseDouble(cardDTO.getFinalDrive().getValue().toString()));

        return card;
    }

    public CardDTO CardToCardDTO(Card card) {

        CardDTO cardDTO = new CardDTO();
        cardDTO.getId().setValue(card.getId());
        cardDTO.getManufacturer().setValue(card.getManufacturer());
        cardDTO.getType().setValue(card.getType());
        cardDTO.getYear().setValue(card.getYear());
        cardDTO.getCountry().setValue(card.getCountry());
        cardDTO.getCornering().setValue(card.getCornering());
        cardDTO.getDoors().setValue(card.getDoors());
        cardDTO.getBody().setValue(card.getBody());
        cardDTO.getSeats().setValue(card.getSeats());
        cardDTO.getDriveWheel().setValue(card.getDriveWheel());
        cardDTO.getFuelType().setValue(card.getFuelType());
        cardDTO.getFuelTankCapacity().setValue(card.getFuelTankCapacity());
        cardDTO.getEngineCapacity().setValue(card.getEngineCapacity());
        cardDTO.getPowerKW().setValue(card.getPowerKW());
        cardDTO.getPowerHP().setValue(card.getPowerHP());
        cardDTO.getMaxTorque().setValue(card.getMaxTorque());
        cardDTO.getTopSpeed().setValue(card.getTopSpeed());
        cardDTO.getAcceleration().setValue(card.getAcceleration());
        cardDTO.getWeight().setValue(card.getWeight());
        cardDTO.getLength().setValue(card.getLength());
        cardDTO.getWidth().setValue(card.getWidth());
        cardDTO.getHeight().setValue(card.getHeight());
        cardDTO.getGroundClearance().setValue(card.getGroundClearance());
        cardDTO.getAbs().setValue(card.getAbs());
        cardDTO.getTractionControl().setValue(card.getTractionControl());
        cardDTO.getImageUrl().setValue(card.getImageUrl());
        cardDTO.getLogoURL().setValue(card.getLogoURL());
        cardDTO.getCarPageUrl().setValue(card.getCarPageUrl());
        cardDTO.getObjectPositionHorizontal().setValue(card.getObjectPositionHorizontal());
        cardDTO.getObjectPositionVertical().setValue(card.getObjectPositionVertical());
        cardDTO.getObjectWidth().setValue(card.getObjectWidth());
        cardDTO.getObjectHeight().setValue(card.getObjectHeight());
        cardDTO.getGear1st().setValue(card.getGear1st());
        cardDTO.getGear2nd().setValue(card.getGear2nd());
        cardDTO.getGear3rd().setValue(card.getGear3rd());
        cardDTO.getGear4th().setValue(card.getGear4th());
        cardDTO.getGear5th().setValue(card.getGear5th());
        cardDTO.getGear6th().setValue(card.getGear6th());
        cardDTO.getFinalDrive().setValue(card.getFinalDrive());

        return cardDTO;
    }
}
