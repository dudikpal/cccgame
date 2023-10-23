package com.games.cccgame.services;

import com.games.cccgame.builder.PlayerCardBuilder;
import com.games.cccgame.commands.BaseCardFilterCommand;
import com.games.cccgame.commands.BulkCreatePlayercardCommand;
import com.games.cccgame.commands.CreatePlayercardCommand;
import com.games.cccgame.dtos.BaseCardDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.helper.Calculate;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.BaseCardFilter;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import com.games.cccgame.repositories.BaseCardRepository;
import com.games.cccgame.repositories.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerCardService {

    private PlayerCardRepository playerCardRepository;

    private BaseCardRepository baseCardRepository;

    private BaseCardService baseCardService;

    private Calculate calculate;

    private CardMapper cardMapper;

    private ModelMapper modelMapper;

    public PlayerCard createPlayerCard(CreatePlayercardCommand command) {

        BaseCard baseCard = modelMapper.map(command, BaseCard.class);
        //calculate.baseCardCornering(baseCard);

        return playerCardRepository.save(
            cardMapper.BaseCardToPlayerCard(baseCard)
        );
    }

    public PlayerCard createPlayerCard(String baseCardId) {

        BaseCard baseCard = baseCardService.getBaseCardById(baseCardId);
        //calculate.baseCardCornering(baseCard);

        return playerCardRepository.save(
            cardMapper.BaseCardToPlayerCard(baseCard)
        );
    }

    public List <PlayerCard> getPlayerCards() {
        /*List<BaseCardDTO> baseCards = baseCardService.getAllBaseCards();
        List<PlayerCard> playerCards = new ArrayList <>();
        for (BaseCard baseCard : baseCards) {
            playerCards.add(cardMapper.BaseCardToPlayerCard(baseCard));
        }*/
        List<PlayerCard> playerCards = playerCardRepository.findAll();

        return playerCards;
    }

    public List <PlayerCard> bulkCreatePlayerCard(List<CreatePlayercardCommand> commands) {

        for (CreatePlayercardCommand command: commands) {

            BaseCard baseCard = modelMapper.map(command, BaseCard.class);
            baseCardService.createBaseCard(baseCard);
            PlayerCard playerCard = cardMapper.BaseCardToPlayerCard(baseCard);
            playerCardRepository.save(playerCard);
        }

        return playerCardRepository.findAll();
    }

    public PlayerCard getPlayerCardSkeleton() {
        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
        playerCardDTO.getBaseCard().setImageUrl("assets/img/placeholder.jpg");

        return playerCardDTO;
    }

    public List <PlayerCard> getFilteredPlayerCards(BaseCardFilterCommand command) {

        List<PlayerCard> playerCards = new ArrayList <>();

        for (BaseCardDTO baseCardDTO : baseCardService.getFilteredBaseCards(command)) {

            BaseCard baseCard = modelMapper.map(baseCardDTO, BaseCard.class);
            PlayerCard playerCard = cardMapper.BaseCardToPlayerCard(baseCard);

            playerCard.setId(new ObjectId().toString());
            playerCards.add(playerCard);
        }

        return playerCards;
    }
}
