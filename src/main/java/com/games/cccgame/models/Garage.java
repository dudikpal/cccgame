package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("garages")
public class Garage {

    @Id
    private String id;

    private String userId;

    private List <PlayerCard> playerCards;

    private Instant createdAt;

    private Instant updatedAt;

    public Garage(List <PlayerCard> playerCards, Instant createdAt) {
        this.playerCards = playerCards;
        this.createdAt = createdAt;
    }
}
