package com.games.cccgame.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.games.cccgame.models.ERole;
import com.games.cccgame.models.Role;

@Repository
public interface RoleRepository extends MongoRepository <Role, Long> {

  Optional<Role> findByName(ERole name);
}
