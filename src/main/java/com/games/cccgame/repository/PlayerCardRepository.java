package com.games.cccgame.repository;

import com.games.cccgame.models.PlayerCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerCardRepository extends MongoRepository<PlayerCard, String> {
}
