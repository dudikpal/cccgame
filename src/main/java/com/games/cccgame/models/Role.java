package com.games.cccgame.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("roles")
public class Role {
  @Id
  private Integer id;

  /*@Enumerated(EnumType.STRING)
  @Column(length = 20)*/
  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}