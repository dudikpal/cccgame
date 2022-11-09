package com.games.cccgame.dtos;

import com.games.cccgame.models.Tunings;
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
    private DataDTO id = new DataDTO <String>("ID", "empty-pCardId");

    private DataDTO card = new DataDTO <CardDTO>("Card", new CardDTO());

    private TuningsDTO tunings = new TuningsDTO();

    private DataDTO createdAt = new DataDTO <LocalDate>("Created date", LocalDate.now());
}
