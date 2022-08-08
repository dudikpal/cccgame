package com.games.cccgame.repository;

import com.games.cccgame.models.Garage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GarageRepository extends MongoRepository<Garage, String> {

    @Query("{'ownerId': ?0}")
    Garage getGarageByOwnerId(String ownerId);
}
