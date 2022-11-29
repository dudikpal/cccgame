package com.games.cccgame.dtos;

import com.games.cccgame.models.Tunings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuningsDTO {

    @Id
    private DataDTO chassis = new DataDTO <Integer>("Tuning Weight", 0);

    private DataDTO engine = new DataDTO <Integer>("Tuning Engine", 0);

    private DataDTO cornering = new DataDTO <Integer>("Tuning Cornering", 0);
}
