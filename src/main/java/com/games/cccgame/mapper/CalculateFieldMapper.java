package com.games.cccgame.mapper;

import com.games.cccgame.dtos.CalculatedFieldsDTO;
import com.games.cccgame.dtos.DataDTO;
import com.games.cccgame.models.CalculatedFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Component
public class CalculateFieldMapper {

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
