/*
package com.games.cccgame;

import com.games.cccgame.command.LoginCommand;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
      System.out.println("In basicauth endpoint");
      return new AuthenticationBean("You are authenticated");
    }

    @PostMapping("/signin")
    public AuthenticationBean signin(@RequestBody LoginCommand command) {
      System.out.println("In signin endpoint");
      System.out.println("Command: " + command);

      if (command.getUsername() == null || command.getPassword() == null ||
        command.getUsername().isBlank() || command.getPassword().isBlank()) {
        */
/*return ResponseEntity.ok()
          .body("Not found");*//*

        return new AuthenticationBean("You are stranger");
      }
      return new AuthenticationBean("You are authenticated");
      */
/*return ResponseEntity.ok()
        .body(
          "{username:" + command.getUsername() + "," +
            "password:" + command.getPassword() + "}"
        );*//*

    }
}
*/
