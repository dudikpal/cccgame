package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
public enum ETuningMultiplier {

    WEIGHT(1.013),
    ENGINE(1.03),
    CORNERING(1.03);

    private double multiplier;

    public double getMultiplier() {
        return multiplier;
    }

    /*public List <Map.Entry<String, Double>> getEntries() {

    }*/
}
