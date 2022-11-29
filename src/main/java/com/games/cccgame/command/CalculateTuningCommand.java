package com.games.cccgame.command;


import com.games.cccgame.models.Tunings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculateTuningCommand {

    private int garageId;

    private int playerCardId;

    private Tunings tunings;
}
