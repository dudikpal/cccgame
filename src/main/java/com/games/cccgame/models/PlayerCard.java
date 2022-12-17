package com.games.cccgame.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.games.cccgame.mapper.CalculateFieldMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("playercards")
public class PlayerCard {

    @Id
    private String id;

    private Card card;

    private Tunings tunings;

    private CalculatedFields calculatedFields;

    private int winRatio;

    private String[] tags;

    private int cornering;

    private double hpPerKg;

    private int sellingValue;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdAt = LocalDate.now();

    public PlayerCard(Card card, LocalDate createdAt) {
        this.card = card;
        this.tunings = new Tunings();
        this.calculatedFields = new CalculatedFields();
        this.createdAt = createdAt;
        this.calculatedFields = new CalculateFieldMapper().initCalculatedFields(card);
    }
}
