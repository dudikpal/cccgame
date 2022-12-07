package com.games.cccgame.controllers;

import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.command.UpgradePlayerCardCommand;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.security.services.UserDetailsImpl;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200"/*, allowedHeaders = "*", allowCredentials = "true"*/)
@AllArgsConstructor
@RestController
@RequestMapping("/api/playercards")
public class PlayerCardController {

    private PlayerCardService playerCardService;


    @GetMapping
    public PlayerCardDTO getPlayerCardSkeleton() {

        return playerCardService.getPlayerCardSkeleton();
    }


    @PostMapping
    public List <PlayerCardDTO> getPlayerCardsToAdmin(@RequestBody String command) {

        return playerCardService.getPlayerCardsToAdmin(command);
    }


    @PostMapping("/tuning/chassis")
    public PlayerCardDTO calculatePlayerCardDTOTuningChassis(@RequestBody CalculateTuningCommand command) {
        PlayerCardDTO playerCardDTO = playerCardService.calculatePlayerCardDTOTuningChassis(command);
        //System.out.println(playerCardDTO);
        return playerCardDTO;
    }
}
