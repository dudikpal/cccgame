package com.games.cccgame.dtos;

import com.games.cccgame.models.Card;
import com.games.cccgame.models.ETuningMultiplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarageDTO {

    private String id;

    private List <PlayerCardDTO> playerCards;

    private LocalDate createdAt;

    private Map <String, Double> tuningMultipliers;
}
