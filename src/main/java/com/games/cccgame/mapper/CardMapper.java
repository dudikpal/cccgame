package com.games.cccgame.mapper;

import com.games.cccgame.builder.PlayerCardBuilder;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CardMapper {

    public PlayerCard BaseCardToPlayerCard(BaseCard baseCard) {

        PlayerCard playerCard = new PlayerCard();

        playerCard.setBaseCard(baseCard);


        if (baseCard.getAcceleration() != null) {
            playerCard.setAcceleration(baseCard.getAcceleration());
        }

        if (baseCard.getCornering() != null) {
            playerCard.setCornering(baseCard.getCornering());
        }

        if (baseCard.getGroundClearance() != null) {
            playerCard.setGroundClearance(baseCard.getGroundClearance());
        }

        if (baseCard.getHeight() != null) {
            playerCard.setHeight(baseCard.getHeight());
        }

        if (baseCard.getPowerHP() != null) {
            playerCard.setPowerHP(baseCard.getPowerHP());
        }

        if (baseCard.getTopSpeed() != null) {
            playerCard.setTopSpeed(baseCard.getTopSpeed());
        }

        if (baseCard.getWeight() != null) {
            playerCard.setWeight(baseCard.getWeight());
        }

        if (baseCard.getWidth() != null) {
            playerCard.setWidth(baseCard.getWidth());
        }

        if (baseCard.getCreatedAt() != null) {
            playerCard.setCreatedAt(Instant.now());
        }

        if (baseCard.getUpdatedAt() != null) {
            playerCard.setUpdatedAt(Instant.now());
        }

        if (baseCard.getValue() != null) {
            playerCard.setValue(baseCard.getValue());
        }
        return playerCard;
    }
}
