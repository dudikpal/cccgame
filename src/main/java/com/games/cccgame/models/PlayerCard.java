package com.games.cccgame.models;

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

    private String cardId;

    private int tuningWeight = 1;

    private int tuningEngine = 1;

    private int tuningHandle = 1;

    private LocalDate createdAt;

    public PlayerCard(String cardId, LocalDate createdAt) {
        this.cardId = cardId;
        this.createdAt = createdAt;
    }
}
