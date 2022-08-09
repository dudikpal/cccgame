package com.games.cccgame.dtos;

import com.games.cccgame.models.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarageDTO {

    private String id;

    private List <CardDTO> cards;
}
