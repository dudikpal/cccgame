package com.games.cccgame.mapper;

import com.fasterxml.jackson.databind.util.EnumValues;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.ETuningMultiplier;
import com.games.cccgame.models.Garage;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class GarageMapper {

    private PlayerCardService playerCardService;

    public GarageDTO garageToDTO(Garage garage) {

        List <PlayerCardDTO> playerCardDTOS = new ArrayList <>();

        for (String playerCardId : garage.getPlayerCards()) {
            playerCardDTOS.add(playerCardService.getPlayerCard(playerCardId));
        }

        GarageDTO garageDTO = new GarageDTO(
            garage.getId(),
            playerCardDTOS,
            garage.getCreatedAt(),
            Arrays.stream(ETuningMultiplier.values()).collect(Collectors.toMap(ETuningMultiplier::name, ETuningMultiplier::getMultiplier))
        );

        return garageDTO;
    }
}
