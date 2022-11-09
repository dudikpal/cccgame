package com.games.cccgame.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tunings {

    private int weight;

    private int engine;

    private int cornering;
}
