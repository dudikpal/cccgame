package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("cards")
public class Card {

    @Id
    private String id;

    private String name;

    public Card(String name) {
        this.name = name;
    }
}
