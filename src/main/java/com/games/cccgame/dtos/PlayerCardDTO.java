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
    private DataDTO id = new DataDTO <String>("ID", null);

    private DataDTO card = new DataDTO <CardDTO>("Card", null);

    private DataDTO tuningWeight = new DataDTO <Integer>("Weight tuning", null);

    private DataDTO tuningEngine = new DataDTO <Integer>("Engine tuning", null);

    private DataDTO tuningCornering = new DataDTO <Integer>("Cornering tuning", null);

    private DataDTO createdAt = new DataDTO <LocalDate>("Created date", null);
}
