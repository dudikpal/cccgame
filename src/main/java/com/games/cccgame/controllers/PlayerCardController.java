package com.games.cccgame.controllers;

import com.games.cccgame.commands.CreatePlayercardCommand;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/playercard")
public class PlayerCardController {

    private PlayerCardService playerCardService;

    @PostMapping
    public PlayerCard createPlayercard(@RequestBody CreatePlayercardCommand command) {
        return playerCardService.createPlayerCard(command);
    }
}
