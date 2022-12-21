package com.games.cccgame.mapper;

import com.games.cccgame.dtos.CalculatedFieldsDTO;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.DataDTO;
import com.games.cccgame.models.CalculatedFields;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.PlayerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Component
public class CalculateFieldMapper {

    public CalculatedFields initCalculatedFields(Card card) {

        CalculatedFields calculatedFields = new CalculatedFields();
        calculatedFields.setCornering(card.getCornering());
        calculatedFields.setHeight(card.getHeight());
        calculatedFields.setGroundClearance(card.getGroundClearance());
        calculatedFields.setPowerHP(card.getPowerHP());
        calculatedFields.setWeight(card.getWeight());
        calculatedFields.setTopSpeed(card.getTopSpeed());
        calculatedFields.setWidth(card.getWidth());
        calculatedFields.setAcceleration(card.getAcceleration());

        return calculatedFields;
    }

    public CalculatedFieldsDTO initCalculatedFieldsDTO(CardDTO cardDTO) {

        CalculatedFieldsDTO calculatedFieldsDTO = new CalculatedFieldsDTO();
        calculatedFieldsDTO.getCornering().setValue(cardDTO.getCornering().getValue());
        calculatedFieldsDTO.getHeight().setValue(cardDTO.getHeight().getValue());
        calculatedFieldsDTO.getGroundClearance().setValue(cardDTO.getGroundClearance().getValue());
        calculatedFieldsDTO.getPowerHP().setValue(cardDTO.getPowerHP().getValue());
        calculatedFieldsDTO.getWeight().setValue(cardDTO.getWeight().getValue());
        calculatedFieldsDTO.getTopSpeed().setValue(cardDTO.getTopSpeed().getValue());
        calculatedFieldsDTO.getWidth().setValue(cardDTO.getWidth().getValue());
        calculatedFieldsDTO.getAcceleration().setValue(cardDTO.getAcceleration().getValue());

        return calculatedFieldsDTO;
    }

    public CalculatedFieldsDTO calculatedFieldsToCalculatedFieldsDTO(CalculatedFields calculatedFields) {

        CalculatedFieldsDTO dto = new CalculatedFieldsDTO();

        dto.getCornering().setValue(calculatedFields.getCornering());
        dto.getAcceleration().setValue(calculatedFields.getAcceleration());
        dto.getHeight().setValue(calculatedFields.getHeight());
        dto.getGroundClearance().setValue(calculatedFields.getGroundClearance());
        dto.getPowerHP().setValue(calculatedFields.getPowerHP());
        dto.getWeight().setValue(calculatedFields.getWeight());
        dto.getTopSpeed().setValue(calculatedFields.getTopSpeed());
        dto.getWidth().setValue(calculatedFields.getWidth());

        return dto;
    }


    public CalculatedFields calculatedFieldsDTOToCalculatedFields(CalculatedFieldsDTO dto) {

        CalculatedFields calculatedFields = new CalculatedFields();

        calculatedFields.setCornering(Integer.parseInt(dto.getCornering().getValue().toString()));
        calculatedFields.setHeight(Integer.parseInt(dto.getHeight().getValue().toString()));
        calculatedFields.setGroundClearance(Integer.parseInt(dto.getGroundClearance().getValue().toString()));
        calculatedFields.setPowerHP(Integer.parseInt(dto.getPowerHP().getValue().toString()));
        calculatedFields.setWeight(Integer.parseInt(dto.getWeight().getValue().toString()));
        calculatedFields.setTopSpeed(Integer.parseInt(dto.getTopSpeed().getValue().toString()));
        calculatedFields.setWidth(Integer.parseInt(dto.getWidth().getValue().toString()));
        calculatedFields.setAcceleration(Double.parseDouble(dto.getAcceleration().getValue().toString()));

        return calculatedFields;
    }
}
