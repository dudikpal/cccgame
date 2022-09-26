package com.games.cccgame.mapper;

import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class PlayerCardMapper {

    private CardService cardService;

    public PlayerCardDTO playerCardToDTO(PlayerCard playerCard) {

        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
            playerCardDTO.getId().setValue("p_" + playerCard.getId());
            playerCardDTO.getCard().setValue(cardService.getCard(Optional.of(playerCard.getCardId())).get(0));
            playerCardDTO.getTuningWeight().setValue(playerCard.getTuningWeight());
            playerCardDTO.getTuningEngine().setValue(playerCard.getTuningEngine());
            playerCardDTO.getTuningCornering().setValue(playerCard.getTuningCornering());
            playerCardDTO.getCreatedAt().setValue(playerCard.getCreatedAt());

        return playerCardDTO;
    }
}
