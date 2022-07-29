package com.example.spring_security_with_angular.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginCommand {

  private String username;

  private String password;
}
