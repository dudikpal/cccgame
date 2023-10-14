package com.games.cccgame.controllers;

import com.games.cccgame.commands.*;
import com.games.cccgame.dtos.BaseCardDTO;
import com.games.cccgame.services.BaseCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/basecard")
public class BaseCardController {

    private BaseCardService baseCardService;

    @PostMapping
    public List<BaseCardDTO> getFilteredBaseCards(@RequestBody BaseCardFilterCommand command) {
        return baseCardService.getFilteredBaseCards(command);
    }

    @GetMapping("/{id}")
    public BaseCardDTO getBaseCardById(@PathVariable String id) {
        return baseCardService.getBaseCardById(id);
    }

    @PostMapping("/create")
    public BaseCardDTO creatBaseCard(@RequestBody CreateBaseCardCommand command) {
        return baseCardService.createBaseCardFromCommand(command);
    }

    @PutMapping
    public BaseCardDTO updateBaseCard(@RequestBody UpdateBaseCardCommand command) {
        System.out.println(command);
        return baseCardService.updateBaseCard(command);
        //return null;
    }

    @PutMapping("/bulk")
    public void bulkUpdateBaseCard(@RequestBody List<UpdateBaseCardCommand> commands) {
        baseCardService.bulkUpdateBaseCard(commands);
    }

    @DeleteMapping
    public void deleteBaseCard(@RequestBody DeleteBaseCardCommand command) {
        baseCardService.deleteBaseCard(command);
    }

    @GetMapping("/skeleton")
    public BaseCardDTO getBaseCardSkeleton() {
        return baseCardService.getBaseCardSkeleton();
    }

    @PostMapping("/bulk")
    public void bulkCreateBaseCard(@RequestBody List<CreateBaseCardCommand> commands) {
        baseCardService.bulkCreateBaseCards(commands);
    }

    @DeleteMapping("/droptable")
    public void dropBaseCardsTable() {
        baseCardService.dropBaseCardsTable();
    }
}
