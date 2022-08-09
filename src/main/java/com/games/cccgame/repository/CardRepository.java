package com.games.cccgame.repository;

import com.games.cccgame.models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
