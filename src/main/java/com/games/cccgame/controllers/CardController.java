package com.games.cccgame.controllers;

import com.games.cccgame.command.CreateCardCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200"/*, allowedHeaders = "*", allowCredentials = "true"*/)
@AllArgsConstructor
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private CardService cardService;

    @GetMapping
    // without param get all cards
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List <CardDTO> getCard(@RequestBody Optional<String> cardId) {
        return cardService.getCard(cardId);
    }


   /* @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminPage() {

        return "<button>Admin page</button>";
    }*/


    @PostMapping
    public CardDTO createCard(@RequestBody CreateCardCommand command) {

        return cardService.createCard(command);
    }


    @PutMapping
    public CardDTO updateCard(@RequestBody UpdateCardCommand command) {

        return cardService.updateCard(command);
    }


    @DeleteMapping("/{id}")
    public void removeCard(@PathVariable String id) {

        cardService.removeCard(id);
    }


    @DeleteMapping
    public void removeAllCard() {

        cardService.removeAllCard();
    }


    @PostMapping("/uploadfile")
    public List <CardDTO> uploadCardsFromFile(@RequestBody String cardsJson) {

        return cardService.uploadCardsFromFile(cardsJson);
    }


    @PostMapping("/find")
    public List<CardDTO> findCardsByCriterias(@RequestBody String command) {

        return cardService.findCardsByCriterias(command);
    }

}
