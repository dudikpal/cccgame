package com.games.cccgame.controllers;

import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.security.jwt.JwtUtils;
import com.games.cccgame.security.services.UserDetailsImpl;
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
    public GarageDTO getGarage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        System.out.println(user);
        GarageDTO garage = garageService.getGarage(user.getGarageId());

        return garage;
    }

}
