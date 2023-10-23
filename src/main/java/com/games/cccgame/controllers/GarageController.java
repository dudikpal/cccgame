package com.games.cccgame.controllers;

/*import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.command.UpgradePlayerCardCommand;*/
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.models.Garage;
import com.games.cccgame.security.jwt.JwtUtils;
import com.games.cccgame.security.services.UserDetailsImpl;
import com.games.cccgame.security.services.UserDetailsServiceImpl;
import com.games.cccgame.services.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/garage")
public class GarageController {

    private GarageService garageService;

    @GetMapping
    public Garage getGarage() {
        return garageService.getGarage(getGarageId());
    }


    /*@PutMapping
    public void updatePlayerCardInGarage(@RequestBody UpgradePlayerCardCommand command) {
        garageService.updatePlayerCardInGarage(getGarageId(), command);
    }


    @PostMapping
    public void updateAllInstancesOfCard(@RequestBody UpdateCardCommand command) {
        garageService.updateAllInstancesOfCard(command);
    }


    @PostMapping("/calculate_tuning/chassis")
    public PlayerCardDTO calculateTuningChassis(@RequestBody CalculateTuningCommand command) {
        return garageService.calculatePlayerCardTuningChassis(getGarageId(), command);
    }


    @PostMapping("/calculate_tuning/engine")
    public PlayerCardDTO calculateTuningEngine(@RequestBody CalculateTuningCommand command) {
        return garageService.calculatePlayerCardTuningEngine(getGarageId(), command);
    }


    @PostMapping("/calculate_tuning/cornering")
    public PlayerCardDTO calculateTuningCornering(@RequestBody CalculateTuningCommand command) {
        return garageService.calculatePlayerCardTuningCornering(getGarageId(), command);
    }*/


    private String getGarageId() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();

        return user.getGarageId();
    }
}
