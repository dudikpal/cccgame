package com.games.cccgame.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.dtos.TuningsDTO;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import com.games.cccgame.services.CardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@Component
public class PlayerCardMapper {

    private CardMapper cardMapper;

    private TuningMapper tuningMapper;

    private CalculateFieldMapper calculateFieldMapper;

    private ObjectMapper objectMapper;

    public PlayerCardDTO playerCardToDTO(PlayerCard playerCard) {

        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
        playerCardDTO.getId().setValue(playerCard.getId());
        playerCardDTO.getCard().setValue(cardMapper.CardToCardDTO(playerCard.getCard()));
        playerCardDTO.setTunings(tuningMapper.tuningsToTuningsDTO(playerCard.getTunings()));
        playerCardDTO.setCalculatedFields(calculateFieldMapper.calculatedFieldsToCalculatedFieldsDTO(playerCard.getCalculatedFields()));
        playerCardDTO.getCreatedAt().setValue(playerCard.getCreatedAt());

        return playerCardDTO;
    }


    public PlayerCard DTOToPlayerCard(PlayerCardDTO playerCardDTO) {

        PlayerCard playerCard = new PlayerCard();

        playerCard.setId((String)playerCardDTO.getId().getValue());
        playerCard.setCard((Card)cardMapper.CardDTOToCard((CardDTO) playerCardDTO.getCard().getValue()));
        playerCard.setTunings( tuningMapper.TuningsDTOToTunings((TuningsDTO) playerCardDTO.getTunings()));
        playerCard.setCalculatedFields(calculateFieldMapper.calculatedFieldsDTOToCalculatedFields(playerCardDTO.getCalculatedFields()));
        playerCard.setCreatedAt(objectMapper.convertValue(playerCardDTO.getCreatedAt().getValue(), LocalDate.class));

        return playerCard;
    }
}
