package com.games.cccgame.repositories;

import com.games.cccgame.models.PlayerCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerCardRepository extends MongoRepository<PlayerCard, String> {

    List <PlayerCard> findAllByBaseCardId(String id);
}
