package com.games.cccgame.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.command.UpdateCardCommand;
import com.games.cccgame.command.UpgradePlayerCardCommand;
import com.games.cccgame.dtos.*;
import com.games.cccgame.mapper.CalculateFieldMapper;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.mapper.TuningMapper;
import com.games.cccgame.models.*;
import com.games.cccgame.repository.PlayerCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.List;


@Log4j2
@Service
@AllArgsConstructor
public class PlayerCardService {

    private CardService cardService;

    private PlayerCardRepository playerCardRepository;

    @Autowired
    private PlayerCardMapper playerCardMapper;

    private CalculateFieldMapper calculateFieldMapper;

    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;


    public PlayerCardDTO getPlayerCard(String playerCardId) {

        PlayerCard playerCard = playerCardRepository.findById(playerCardId).get();
        PlayerCardDTO playerCardDTO = playerCardMapper.playerCardToDTO(playerCard);

        return playerCardDTO;
    }


    public PlayerCard getRawPlayerCard(String playerCardId) {

        return playerCardRepository.findById(playerCardId).get();
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
                playerCardDTO.setCalculatedFields(calculateFieldMapper.initCalculatedFieldsDTO(cardDTO));
                return playerCardDTO;
            })
            .toList();

        return filteredPlayerCards;
    }


    public PlayerCardDTO getPlayerCardSkeleton() {

        PlayerCardDTO playerCardDTO = new PlayerCardDTO();
        return playerCardDTO;
    }


    public void calculatePlayerCardTunings(PlayerCard playerCard) {
        //calculatePlayerCardTuningChassis(playerCard)
    }


    public PlayerCardDTO calculatePlayerCardTuningEngine(CalculateTuningCommand command) {

        PlayerCard upgradedCard = playerCardMapper.DTOToPlayerCard(modelMapper.map(command, PlayerCardDTO.class));
        tuningEngine(upgradedCard);

        return playerCardMapper.playerCardToDTO(upgradedCard);
    }


    public PlayerCardDTO calculatePlayerCardTuningCornering(CalculateTuningCommand command) {

        PlayerCard upgradedCard = playerCardMapper.DTOToPlayerCard(modelMapper.map(command, PlayerCardDTO.class));
        tuningCornering(upgradedCard);

        return playerCardMapper.playerCardToDTO(upgradedCard);
    }


    public PlayerCard calculatePlayerCardTuningChassis(CalculateTuningCommand command) {

        PlayerCardDTO playerCardDTO = commandToPlayercardDTO(command);
        PlayerCard upgradedCard = playerCardMapper.DTOToPlayerCard(playerCardDTO);

        return tuningChassis(upgradedCard);
    }

    public PlayerCardDTO calculatePlayerCardDTOTuningChassis(CalculateTuningCommand command) {
        return playerCardMapper.playerCardToDTO(calculatePlayerCardTuningChassis(command));
    }


    private PlayerCard tuningChassis(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.CHASSIS.getMultiplier();
        int tuningLevel = playerCard.getTunings().getChassis();

        playerCard.getCalculatedFields().setAcceleration(calcDecreaseDouble(tuningMultiplier, tuningLevel, playerCard.getCard().getAcceleration()));
        playerCard.getCalculatedFields().setWeight(calcDecrease(tuningMultiplier, tuningLevel, playerCard.getCard().getWeight()));
        playerCard.getCalculatedFields().setTopSpeed(calcIncrease(tuningMultiplier, tuningLevel, playerCard.getCard().getTopSpeed()));

        return playerCard;
    }


    private <T extends PlayerCardDTO> PlayerCardDTO commandToPlayercardDTO(T command) {
        // a DataDTO miatt kell objectMappelni
        PlayerCardDTO playerCardDTO = objectMapper.convertValue(command, PlayerCardDTO.class);
        CardDTO cardDTO = objectMapper.convertValue(command.getCard().getValue(), CardDTO.class);
        playerCardDTO.getCard().setValue(cardDTO);

        return playerCardDTO;
    }


    private PlayerCard tuningEngine(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.ENGINE.getMultiplier();
        int tuningLevel = playerCard.getTunings().getEngine();

        playerCard.getCalculatedFields().setAcceleration(calcDecreaseDouble(tuningMultiplier, tuningLevel, playerCard.getCard().getAcceleration()));
        playerCard.getCalculatedFields().setPowerHP(calcIncrease(tuningMultiplier, tuningLevel, playerCard.getCard().getPowerHP()));
        playerCard.getCalculatedFields().setTopSpeed(calcIncrease(tuningMultiplier, tuningLevel, playerCard.getCard().getTopSpeed()));

        return playerCard;
    }


    private PlayerCard tuningCornering(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.CORNERING.getMultiplier();
        int tuningLevel = playerCard.getTunings().getCornering();

        playerCard.getCalculatedFields().setWidth(calcIncrease(tuningMultiplier, tuningLevel, playerCard.getCard().getWidth()));
        playerCard.getCalculatedFields().setHeight(calcDecrease(tuningMultiplier, tuningLevel, playerCard.getCard().getHeight()));
        playerCard.getCalculatedFields().setGroundClearance(calcDecrease(tuningMultiplier, tuningLevel, playerCard.getCard().getGroundClearance()));

        return playerCard;
    }


    private int calcIncrease(double tuningMultiplier, int tuningLevel, int cardValue) {
        return (int)(cardValue * (1 + (tuningLevel * tuningMultiplier)));
    }


    private int calcDecrease(double tuningMultiplier, int tuningLevel, int cardValue) {
        return (int)(cardValue * (1 - (tuningLevel * tuningMultiplier)));
    }


    private double calcDecreaseDouble(double tuningMultiplier, int tuningLevel, double cardValue) {

        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        double result = cardValue * (1 - (tuningLevel * tuningMultiplier));

        return Double.parseDouble(new DecimalFormat("0.00", decimalFormatSymbols).format(result));
    }
}
