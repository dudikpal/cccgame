package com.games.cccgame.models;

import lombok.*;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCardFilter {

    private SimpleValue[] simpleValues;

    private MultipleValue[] multipleValues;

    private Between[] betweens;

    @Override
    public String toString() {
        return "BaseCardFilter{" +
            "simpleValues=" + Arrays.toString(simpleValues) +
            ", multipleValues=" + Arrays.toString(multipleValues) +
            ", betweens=" + Arrays.toString(betweens) +
            '}';
    }
}

