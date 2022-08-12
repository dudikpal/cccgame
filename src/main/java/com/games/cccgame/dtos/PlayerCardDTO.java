package com.games.cccgame.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCardDTO {

    @Id
    private String id;

    private CardDTO card;

    private int tuningWeight;

    private int tuningEngine;

    private int tuningHandle;

    private LocalDate createdAt;
}
