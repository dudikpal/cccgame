package com.games.cccgame.commands;

import com.games.cccgame.models.BaseCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkUpdateBaseCardCommand {

    private List <UpdateBaseCardCommand> updateBaseCardCommands;
}
