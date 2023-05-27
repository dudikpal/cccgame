package com.games.cccgame.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("playercards")
public class PlayerCard {

    @Id
    private String id;

    private BaseCard baseCard;

    private Tunings tunings;

    private int weight;

    private int topSpeed;

    private double acceleration;

    private int powerHP;

    private int width;

    private int height;

    private int cornering;

    private int groundClearance;

    private ArrayList<String> tags;

    private int win;

    private int loss;

    private int value;

    private Instant createdAt;

    private Instant updatedAt;
}
