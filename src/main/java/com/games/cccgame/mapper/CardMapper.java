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
        card.setYear((int) cardDTO.getYear().getValue());
        card.setCountry((String) cardDTO.getCountry().getValue());
        card.setDoors((int) cardDTO.getDoors().getValue());
        card.setBody((String) cardDTO.getBody().getValue());
        card.setSeats((int) cardDTO.getSeats().getValue());
        card.setDriveWheel((String) cardDTO.getDriveWheel().getValue());
        card.setFuelType((String) cardDTO.getFuelType().getValue());
        card.setFuelTankCapacity((int) cardDTO.getFuelTankCapacity().getValue());
        card.setEngineCapacity((int) cardDTO.getEngineCapacity().getValue());
        card.setPowerKW((int) cardDTO.getPowerKW().getValue());
        card.setPowerHP((int) cardDTO.getPowerHP().getValue());
        card.setMaxTorque((int) cardDTO.getMaxTorque().getValue());
        card.setTopSpeed((int) cardDTO.getTopSpeed().getValue());
        card.setAcceleration((double) cardDTO.getAcceleration().getValue());
        card.setWeight((int) cardDTO.getWeight().getValue());
        card.setLength((int) cardDTO.getLength().getValue());
        card.setWidth((int) cardDTO.getWidth().getValue());
        card.setHeight((int) cardDTO.getHeight().getValue());
        card.setGroundClearance((int) cardDTO.getGroundClearance().getValue());
        card.setAbs((String) cardDTO.getAbs().getValue());
        card.setTractionControl((String) cardDTO.getTractionControl().getValue());
        card.setImageUrl((String) cardDTO.getImageUrl().getValue());
        card.setLogoURL((String) cardDTO.getLogoURL().getValue());
        card.setCarPageUrl((String) cardDTO.getCarPageUrl().getValue());
        card.setObjectPositionHorizontal((String) cardDTO.getObjectPositionHorizontal().getValue());
        card.setObjectPositionVertical((String) cardDTO.getObjectPositionVertical().getValue());
        card.setObjectWidth((String) cardDTO.getObjectWidth().getValue());
        card.setObjectHeight((String) cardDTO.getObjectHeight().getValue());
        card.setGear1st((double) cardDTO.getGear1st().getValue());
        card.setGear2nd((double) cardDTO.getGear2nd().getValue());
        card.setGear3rd((double) cardDTO.getGear3rd().getValue());
        card.setGear4th((double) cardDTO.getGear4th().getValue());
        card.setGear5th((double) cardDTO.getGear5th().getValue());
        card.setGear6th((double) cardDTO.getGear6th().getValue());
        card.setFinalDrive((double) cardDTO.getFinalDrive().getValue());

        return card;
    }

    public CardDTO CardToCardDTO(Card card) {

        CardDTO cardDTO = new CardDTO();
        cardDTO.getId().setValue(card.getId());
        cardDTO.getManufacturer().setValue(card.getManufacturer());
        cardDTO.getType().setValue(card.getType());
        cardDTO.getYear().setValue(card.getYear());
        cardDTO.getCountry().setValue(card.getCountry());
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
