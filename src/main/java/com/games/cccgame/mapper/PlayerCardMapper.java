package com.games.cccgame.mapper;

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

    public PlayerCardDTO playerCardToDTO(PlayerCard playerCard) {

        /*System.out.println("mapper elott");
        System.out.println(playerCard);*/
        PlayerCardDTO playerCardDTO = new PlayerCardDTO();

        playerCardDTO.getId().setValue(playerCard.getId());
        playerCardDTO.getCard().setValue(cardMapper.CardToCardDTO(playerCard.getCard()));
        playerCardDTO.setTunings(tuningMapper.tuningsToTuningsDTO(playerCard.getTunings()));
        playerCardDTO.getCreatedAt().setValue(playerCard.getCreatedAt());
        /*System.out.println("mapper utaan");
        System.out.println(playerCardDTO);*/
        return playerCardDTO;
    }

    public PlayerCard DTOToPlayerCard(PlayerCardDTO playerCardDTO) {

        PlayerCard playerCard = new PlayerCard();

        playerCard.setId((String)playerCardDTO.getId().getValue());
        playerCard.setCard((Card)cardMapper.CardDTOToCard((CardDTO) playerCardDTO.getCard().getValue()));
        playerCard.setTunings( tuningMapper.TuningsDTOToTunings((TuningsDTO) playerCardDTO.getTunings()));
        playerCard.setCreatedAt((LocalDate) playerCardDTO.getCreatedAt().getValue());

        return playerCard;
    }
}
