package com.games.cccgame.mapper;

import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.Garage;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
            garage.getCreatedAt()
        );

        return garageDTO;
    }
}
