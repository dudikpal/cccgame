package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("garages")
public class Garage {

    @Id
    private String id;

    private List <PlayerCard> playerCards;

    private LocalDate createdAt;

    public Garage(List <PlayerCard> playerCards, LocalDate createdAt) {
        this.playerCards = playerCards;
        this.createdAt = createdAt;
    }
}
