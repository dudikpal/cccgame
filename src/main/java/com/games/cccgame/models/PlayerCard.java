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

    private BaseCard baseCard = new BaseCard();

    private Tunings tunings = new Tunings();

    private Integer weight;

    private Integer topSpeed;

    private Double acceleration;

    private Integer powerHP;

    private Integer width;

    private Integer height;

    private Integer cornering;

    private Integer groundClearance;

    private Integer win;

    private Integer loss;

    private Integer value;

    private Instant createdAt;

    private Instant updatedAt;
}
