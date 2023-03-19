package com.games.cccgame.controllers;

import com.games.cccgame.commands.CreateBaseCardCommand;
import com.games.cccgame.commands.UpdateBaseCardCommand;
import com.games.cccgame.dtos.BaseCardDTO;
import com.games.cccgame.services.BaseCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/basecard")
public class BaseCardController {

    private BaseCardService baseCardService;

    @GetMapping("/{id}")
    public BaseCardDTO getBaseCardById(@PathVariable String id) {
        return baseCardService.getBaseCardById(id);
    }

    @PostMapping
    public BaseCardDTO creatBaseCard(@RequestBody CreateBaseCardCommand command) {
        return baseCardService.createBaseCard(command);
    }

    @PutMapping
    public BaseCardDTO updateBaseCard(@RequestBody UpdateBaseCardCommand command) {
        return baseCardService.updateBaseCard(command);
    }

    @DeleteMapping("/{id}")
    public void deleteBaseCard(@PathVariable String id) {
        baseCardService.deleteBaseCard(id);
    }
}
