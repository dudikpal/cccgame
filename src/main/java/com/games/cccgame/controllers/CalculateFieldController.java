package com.games.cccgame.controllers;

import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.services.CalculateFieldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/calculateTunings")
public class CalculateFieldController {

    private CalculateFieldService calculateFieldService;

    public void calculateTuningFields(@RequestBody CalculateTuningCommand command) {
        calculateFieldService.calculateTuningFields(command);
    }
}
