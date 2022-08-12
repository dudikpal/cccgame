package com.games.cccgame.services;

import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repository.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerCardService {

    private CardService cardService;

    private PlayerCardRepository playerCardRepository;

    @Autowired
    private PlayerCardMapper playerCardMapper;

    public PlayerCardDTO getPlayerCard(String playerCardId) {

        PlayerCard playerCard = playerCardRepository.findById(playerCardId).get();
        PlayerCardDTO playerCardDTO = playerCardMapper.playerCardToDTO(playerCard);

        return playerCardDTO;
    }

    public PlayerCardDTO createPlayerCard(String cardId) {

        PlayerCard playerCard = playerCardRepository
            .save(new PlayerCard(cardId, LocalDate.now()));

        return playerCardMapper.playerCardToDTO(playerCard);
    }
}
