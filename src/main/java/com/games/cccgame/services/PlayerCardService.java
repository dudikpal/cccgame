package com.games.cccgame.services;

import com.games.cccgame.builder.PlayerCardBuilder;
import com.games.cccgame.commands.CreatePlayercardCommand;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repositories.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerCardService {

    private PlayerCardRepository playerCardRepository;

    private CardMapper cardMapper;

    private ModelMapper modelMapper;

    public PlayerCard createPlayerCard(CreatePlayercardCommand command) {

        BaseCard baseCard = modelMapper.map(command, BaseCard.class);

        return playerCardRepository.save(
            cardMapper.BaseCardToPlayerCard(baseCard)
        );
    }
}
