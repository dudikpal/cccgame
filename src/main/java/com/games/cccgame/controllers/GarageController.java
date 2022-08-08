package com.games.cccgame.controllers;

import com.games.cccgame.command.GarageCommand;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.models.User;
import com.games.cccgame.security.jwt.JwtUtils;
import com.games.cccgame.security.services.UserDetailsServiceImpl;
import com.games.cccgame.services.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/garage")
public class GarageController {

    private GarageService garageService;

    private JwtUtils jwtUtils;

    private UserDetailsServiceImpl userService;

    @GetMapping
    public GarageDTO getGarage(@RequestHeader(name="Authorization") String token) {

        System.out.println("token: " + token);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Authentication: " + auth.getPrincipal());
        GarageDTO garage = garageService.getGarage();

        System.out.println(garage);
        return garage;
    }
}
