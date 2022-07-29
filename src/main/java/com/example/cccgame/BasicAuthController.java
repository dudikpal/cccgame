package com.example.cccgame;

import com.example.cccgame.command.LoginCommand;
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
    public String signin(@RequestBody LoginCommand command) {
      System.out.println("In signin endpoint");
      System.out.println(command);
      return "You are signined";
    }
}
