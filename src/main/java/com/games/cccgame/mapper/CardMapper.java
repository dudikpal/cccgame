package com.games.cccgame.mapper;

import com.games.cccgame.builder.PlayerCardBuilder;
import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CardMapper {

    public PlayerCard BaseCardToPlayerCard(BaseCard baseCard) {

        PlayerCard playerCard = PlayerCardBuilder.aPlayerCard()
            .withBaseCard(baseCard)
            .withAcceleration(baseCard.getAcceleration())
            .withCornering(baseCard.getCornering())
            .withGroundClearance(baseCard.getGroundClearance())
            .withHeight(baseCard.getHeight())
            .withPowerHP(baseCard.getPowerHP())
            .withTopSpeed(baseCard.getTopSpeed())
            .withWeight(baseCard.getWeight())
            .withWidth(baseCard.getWidth())
            .withTags(new ArrayList<String>())
            .withTunings(new Tunings(0, 0, 0))
            .withCreatedAt(Instant.now())
            .withUpdatedAt(Instant.now())
            .withValue(baseCard.getValue())
            .withLoss(0)
            .withWin(0)
            .build();

        return playerCard;
    }
}
