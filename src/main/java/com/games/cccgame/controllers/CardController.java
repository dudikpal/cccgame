package com.games.cccgame.controllers;

import com.games.cccgame.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://cccgame.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200"/*, allowedHeaders = "*", allowCredentials = "true"*/)
@AllArgsConstructor
@RestController
//@RequestMapping
@RequestMapping("/api/cards")
public class CardController {

    private CardService cardService;

    @GetMapping
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String getAllCards() {

        return cardService.getAllCards();
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminPage() {

        return "<button>Admin page</button>";
    }
}
