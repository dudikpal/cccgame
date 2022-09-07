package com.games.cccgame.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200"/*, allowedHeaders = "*", allowCredentials = "true"*/)
@AllArgsConstructor
@RestController
@RequestMapping("/api/playerCards")
public class PlayerCardController {


}
