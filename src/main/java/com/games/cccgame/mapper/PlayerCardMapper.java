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

        PlayerCardDTO playerCardDTO = new PlayerCardDTO(
            playerCard.getId(),
            cardService.getCard(Optional.of(playerCard.getCardId())).get(0),
            playerCard.getTuningWeight(),
            playerCard.getTuningEngine(),
            playerCard.getTuningHandle(),
            playerCard.getCreatedAt()
        );

        return playerCardDTO;
    }
}
