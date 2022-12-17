package com.games.cccgame.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.command.UpgradePlayerCardCommand;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.CalculateFieldMapper;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import com.games.cccgame.repository.GarageRepository;
import com.games.cccgame.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
@Log4j2
public class GarageService {

    private GarageRepository garageRepository;

    private PlayerCardService playerCardService;

    @Autowired
    private CardService cardService;

    private PlayerCardMapper playerCardMapper;

    private ObjectMapper objectMapper;

    private ModelMapper modelMapper;

    @Autowired
    private GarageMapper garageMapper;

    private CardMapper cardMapper;

    private CalculateFieldMapper calculateFieldMapper;


    public GarageDTO getGarage(String garageId) {

        Garage garage = garageRepository.findById(garageId).get();
        GarageDTO garageDTO = garageMapper.garageToDTO(garage);

        return garageDTO;
    }


    public Garage getRawGarage(String garageId) {
        return garageRepository.findById(garageId).get();
    }


    public List<String> getAllGarageIds() {
        List<String> garages = new ArrayList <>();
        garages = garageRepository.findAll().stream()
            .map(garage -> garage.getId())
            .toList();

        return garages;
    }


    public GarageDTO createGarage() {

        Garage garage = new Garage(List.of(), LocalDate.now());
        List <PlayerCard> playerCards = new ArrayList <>();

        playerCards.add(playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("c_audi-a8-55-tfsi-quattro_80368")));
        playerCards.add(playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("c_audi-a8-55-tfsi-quattro_80368")));
        playerCards.add(playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("c_2007_audi_a4_71215")));

        playerCards.add(playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("c_2-i-turbo-231hp-8302")));
        playerCards.add(playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("c_0-tsi-evo-300hp-dsg-42357")));
        PlayerCard playerCard = playerCardMapper.DTOToPlayerCard(playerCardService.createPlayerCard("tsi-evo-300hp-dsg-42357"));
        Tunings tunings = new Tunings();
        playerCard.setTunings(tunings);
        playerCards.add(playerCard);
        garage.setPlayerCards(playerCards);
        garageRepository.save(garage);

        return garageMapper.garageToDTO(garage);
    }


    public void updatePlayerCardInGarage(String garageId, UpgradePlayerCardCommand command) {

        PlayerCardDTO upgradedCardDTO = objectMapper.convertValue(command, PlayerCardDTO.class);
        CardDTO cardDTO = objectMapper.convertValue(command.getCard().getValue(), CardDTO.class);
        // ezen még refaktorálni kell, csak azrért nem mapperrel van csinálva, mert circ dependency hibát okoz
        upgradedCardDTO.getCard().setValue(cardDTO);
        GarageDTO garageDTO = getGarage(garageId);
        PlayerCardDTO originalPlayerCardDTO = garageDTO.getPlayerCards().stream()
            .filter(card -> card.getId().getValue().equals(upgradedCardDTO.getId().getValue()))
            .findFirst()
            .get();

        garageDTO.getPlayerCards().set(garageDTO.getPlayerCards().indexOf(originalPlayerCardDTO), upgradedCardDTO);
        garageRepository.save(garageMapper.dToToGarage(garageDTO));
        log.info("Updating PlayerCard: (" + upgradedCardDTO.getId().getValue() + ") in garage: (" + garageId + ") success");

    }

// engine tuning után chassist választva a weightet leveszi, de az acc meg a topspeed változatlan marad
    public void updateAllInstancesOfCard(UpdateCardCommand command) {

        List<String> garageIds = getAllGarageIds();
        Card updatedCard = cardMapper.CardDTOToCard(command);
        cardService.updateCard(command);

        for (String garageId : garageIds) {
            Garage garage = getRawGarage(garageId);
            List<PlayerCard> playerCards = garage.getPlayerCards();
            List<PlayerCard> filteredPlayerCards = playerCards.stream()
                .filter(playerCard -> playerCard.getCard().getId().equals(updatedCard.getId()))
                .toList();

            for (PlayerCard playerCard : filteredPlayerCards) {

                int index = garage.getPlayerCards().indexOf(playerCard);
                playerCard.setCard(updatedCard);
                playerCard.setCalculatedFields(calculateFieldMapper.initCalculatedFields(updatedCard));
                garage.getPlayerCards().set(index, calculateAllTunings(playerCard));
            }
            garageRepository.save(garage);
        }
    }


    private PlayerCard calculateAllTunings(PlayerCard playerCard) {
        playerCardService.calculatePlayerCardTuningChassis(playerCard);
        playerCardService.calculatePlayerCardTuningEngine(playerCard);
        playerCardService.calculatePlayerCardTuningCornering(playerCard);

        return playerCard;
    }


    public PlayerCardDTO calculatePlayerCardTuningChassis(String garageId, CalculateTuningCommand command) {

        Garage garage = getRawGarage(garageId);
        List<PlayerCard> playerCards = garage.getPlayerCards();
        int playerCardIndex = IntStream.range(0, playerCards.size())
            .filter(index -> playerCards.get(index).getId().equals(command.getId().getValue()))
            .findFirst()
            .getAsInt();

        PlayerCard upgradedPlayerCard = playerCardService.calculatePlayerCardTuningChassis(playerCardService.commandToPlayercard(command));
        garage.getPlayerCards().set(playerCardIndex, upgradedPlayerCard);
        garageRepository.save(garage);

        return playerCardMapper.playerCardToDTO(upgradedPlayerCard);
    }

    public PlayerCardDTO calculatePlayerCardTuningEngine(String garageId, CalculateTuningCommand command) {

        Garage garage = getRawGarage(garageId);
        List<PlayerCard> playerCards = garage.getPlayerCards();
        int playerCardIndex = IntStream.range(0, playerCards.size())
            .filter(index -> playerCards.get(index).getId().equals(command.getId().getValue()))
            .findFirst()
            .getAsInt();

        PlayerCard upgradedPlayerCard = playerCardService.calculatePlayerCardTuningEngine(playerCardService.commandToPlayercard(command));
        garage.getPlayerCards().set(playerCardIndex, upgradedPlayerCard);
        garageRepository.save(garage);

        return playerCardMapper.playerCardToDTO(upgradedPlayerCard);
    }

    public PlayerCardDTO calculatePlayerCardTuningCornering(String garageId, CalculateTuningCommand command) {

        Garage garage = getRawGarage(garageId);
        List<PlayerCard> playerCards = garage.getPlayerCards();
        int playerCardIndex = IntStream.range(0, playerCards.size())
            .filter(index -> playerCards.get(index).getId().equals(command.getId().getValue()))
            .findFirst()
            .getAsInt();

        PlayerCard upgradedPlayerCard = playerCardService.calculatePlayerCardTuningCornering(playerCardService.commandToPlayercard(command));
        garage.getPlayerCards().set(playerCardIndex, upgradedPlayerCard);
        garageRepository.save(garage);

        return playerCardMapper.playerCardToDTO(upgradedPlayerCard);
    }


}
