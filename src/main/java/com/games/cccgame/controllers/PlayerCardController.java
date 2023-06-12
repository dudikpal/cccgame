package com.games.cccgame.controllers;

import com.games.cccgame.commands.BaseCardFilterCommand;
import com.games.cccgame.commands.BulkCreatePlayercardCommand;
import com.games.cccgame.commands.CreatePlayercardCommand;
import com.games.cccgame.models.BaseCardFilter;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/playercard")
public class PlayerCardController {

    private PlayerCardService playerCardService;

    @GetMapping
    public List <PlayerCard> getPlayerCards() {
        return playerCardService.getPlayerCards();
    }

    @PostMapping
    public List<PlayerCard> getFilteredPlayerCards(@RequestBody BaseCardFilterCommand command) {
        return playerCardService.getFilteredPlayerCards(command);
    }

    @PostMapping("/create")
    public PlayerCard createPlayercard(@RequestBody CreatePlayercardCommand command) {
        return playerCardService.createPlayerCard(command);
    }

    @PostMapping("/bulk")
    public List<PlayerCard> bulkCreatePlayercard(@RequestBody List<CreatePlayercardCommand> commands) {
        return playerCardService.bulkCreatePlayerCard(commands);
    }

    @GetMapping("/skeleton")
    public PlayerCard getPlayerCardSkeleton() {
        return playerCardService.getPlayerCardSkeleton();
    }
}
