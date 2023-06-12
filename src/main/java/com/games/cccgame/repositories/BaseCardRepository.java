package com.games.cccgame.repositories;

import com.games.cccgame.models.BaseCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseCardRepository extends MongoRepository<BaseCard, String> {

}
