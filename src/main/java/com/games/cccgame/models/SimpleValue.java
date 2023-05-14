package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleValue {

    private String name;

    private String value;

    @Override
    public String toString() {
        return "SimpleValue{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
