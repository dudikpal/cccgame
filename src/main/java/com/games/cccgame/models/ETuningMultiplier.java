package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
public enum ETuningMultiplier {

    CHASSIS(0.013),
    ENGINE(0.03),
    CORNERING(0.03);

    private double multiplier;

    public double getMultiplier() {
        return multiplier;
    }
}
