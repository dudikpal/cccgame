package com.games.cccgame.mapper;

import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Component
public class PlayerCardMapper {

    private CardService cardService;

    private CardMapper cardMapper;

    public PlayerCardDTO playerCardToDTO(PlayerCard playerCard) {

        PlayerCardDTO playerCardDTO = new PlayerCardDTO();

        playerCardDTO.getId().setValue(playerCard.getId());
        playerCardDTO.getCard().setValue(cardMapper.CardToCardDTO(cardService.getCard(playerCard.getCard().getId())));
        playerCardDTO.getTuningWeight().setValue(playerCard.getTuningWeight());
        playerCardDTO.getTuningEngine().setValue(playerCard.getTuningEngine());
        playerCardDTO.getTuningCornering().setValue(playerCard.getTuningCornering());
        playerCardDTO.getCreatedAt().setValue(playerCard.getCreatedAt());

        return playerCardDTO;
    }

    public PlayerCard DTOToPlayerCard(PlayerCardDTO playerCardDTO) {

        PlayerCard playerCard = new PlayerCard();

        playerCard.setId((String)playerCardDTO.getId().getValue());
        playerCard.setCard((Card)cardMapper.CardDTOToCard((CardDTO) playerCardDTO.getCard().getValue()));
        playerCard.setTuningWeight((Integer)playerCardDTO.getTuningWeight().getValue());
        playerCard.setTuningEngine((Integer)playerCardDTO.getTuningEngine().getValue());
        playerCard.setTuningCornering((Integer)playerCardDTO.getTuningCornering().getValue());
        playerCard.setCreatedAt((LocalDate) playerCardDTO.getCreatedAt().getValue());

        return playerCard;
    }
}
