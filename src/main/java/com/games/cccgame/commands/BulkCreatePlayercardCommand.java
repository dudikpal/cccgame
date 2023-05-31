package com.games.cccgame.commands;

import com.games.cccgame.models.BaseCard;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BulkCreatePlayercardCommand {

    private List <BaseCard> baseCards;
}
