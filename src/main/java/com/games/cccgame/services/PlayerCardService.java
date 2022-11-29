package com.games.cccgame.services;

import com.games.cccgame.command.UpgradePlayerCardCommand;
import com.games.cccgame.dtos.*;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.mapper.TuningMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repository.PlayerCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


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

        Card card = cardService.getCard(cardId);
        PlayerCard playerCard = playerCardRepository.save(new PlayerCard(card, LocalDate.now()));

        return playerCardMapper.playerCardToDTO(playerCard);
    }


    public List<PlayerCardDTO> getPlayerCardsToAdmin(String command) {

        List<PlayerCardDTO> playerCardDTOS = findCardsByCriterias(command);

        return playerCardDTOS;
    }


    public List <PlayerCardDTO> findCardsByCriterias(String command) {

        List <CardDTO> filteredCards = cardService.findCardsByCriterias(command);
        List <PlayerCardDTO> filteredPlayerCards = filteredCards.stream()
            .map(cardDTO -> {
                PlayerCardDTO playerCardDTO = new PlayerCardDTO();
                playerCardDTO.setCard(new DataDTO("Card", cardDTO));
                return playerCardDTO;
            })
            .toList();

        return filteredPlayerCards;
    }


    public PlayerCardDTO getPlayerCardSkeleton() {

        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
        return playerCardDTO;
    }
}
