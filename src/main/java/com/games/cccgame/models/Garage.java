package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("garages")
public class Garage {

    @Id
    private String id;

    private String ownerId;

    private List <Card> cards;

    public Garage(String ownerId, List <Card> cards) {
        this.ownerId = ownerId;
        this.cards = cards;
    }
}
