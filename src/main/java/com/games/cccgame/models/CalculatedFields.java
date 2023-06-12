package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedFields {

    private int weight;

    private int topSpeed;

    private double acceleration;

    private int powerHP;

    private int width;

    private int height;

    private int cornering;

    private int groundClearance;
}
