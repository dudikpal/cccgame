package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
public enum ETuningMultiplier {

    CHASSIS(0.1),
    ENGINE(0.1),
    CORNERING(0.1),
    DECREASER(0.9);
/*
CHASSIS(0.016),
    ENGINE(0.03),
    CORNERING(0.03);
*/

    private double multiplier;

    public double getMultiplier() {
        return multiplier;
    }
}
