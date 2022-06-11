package com.games.cccgame;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @GetMapping
    public String getPlayers() {

        LocalDateTime now = LocalDateTime.now();
        return """
                Players from backend:
                player1
                player2
                """ + now;

    }
}
