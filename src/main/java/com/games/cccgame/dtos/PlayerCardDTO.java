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
    private DataDTO id = new DataDTO("ID", null);

    private DataDTO card = new DataDTO("Card", null);

    private DataDTO tuningWeight = new DataDTO("Weight tuning", null);

    private DataDTO tuningEngine = new DataDTO("Engine tuning", null);

    private DataDTO tuningCornering = new DataDTO("Cornering tuning", null);

    private DataDTO createdAt = new DataDTO("Created date", null);
}
