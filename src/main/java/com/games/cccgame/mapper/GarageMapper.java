package com.games.cccgame.mapper;

import com.fasterxml.jackson.databind.util.EnumValues;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.ETuningMultiplier;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
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

    private PlayerCardMapper playerCardMapper;

    public GarageDTO garageToDTO(Garage garage) {

        List <PlayerCardDTO> playerCardDTOS = new ArrayList <>();

        for (PlayerCard playerCard : garage.getPlayerCards()) {
            playerCardDTOS.add(playerCardMapper.playerCardToDTO(playerCard));
        }

        GarageDTO garageDTO = new GarageDTO(
            garage.getId(),
            playerCardDTOS,
            garage.getCreatedAt(),
            Arrays.stream(ETuningMultiplier.values()).collect(Collectors.toMap(ETuningMultiplier::name, ETuningMultiplier::getMultiplier))
        );

        return garageDTO;
    }

    public Garage dToToGarage(GarageDTO garageDTO) {

        List<PlayerCard> playerCards = garageDTO.getPlayerCards().stream()
            .map(cardDTO -> playerCardMapper.DTOToPlayerCard(cardDTO))
            .toList();
        Garage garage = new Garage(
            garageDTO.getId(),
            playerCards,
            garageDTO.getCreatedAt()
        );
        return garage;
    }
}
