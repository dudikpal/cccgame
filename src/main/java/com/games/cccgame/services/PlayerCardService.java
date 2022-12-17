package com.games.cccgame.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.CalculateTuningCommand;
import com.games.cccgame.dtos.*;
import com.games.cccgame.mapper.CalculateFieldMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
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


    public PlayerCard calculatePlayerCardAllTunings(PlayerCard playerCard) {
        calculatePlayerCardTuningChassis(playerCard);
        calculatePlayerCardTuningEngine(playerCard);
        calculatePlayerCardTuningCornering(playerCard);

        return playerCard;
    }


    public PlayerCard calculatePlayerCardTuningEngine(PlayerCard playerCard) {

        return tuningEngine(playerCard);
    }


    public PlayerCard calculatePlayerCardTuningCornering(PlayerCard playerCard) {

        return tuningCornering(playerCard);
    }


    public PlayerCard calculatePlayerCardTuningChassis(PlayerCard playerCard) {

        return tuningChassis(playerCard);
    }

    public PlayerCardDTO calculatePlayerCardDTOTuningChassis(CalculateTuningCommand command) {
        return playerCardMapper.playerCardToDTO(calculatePlayerCardTuningChassis(commandToPlayercard(command)));
    }


    public PlayerCardDTO calculatePlayerCardDTOTuningEngine(CalculateTuningCommand command) {
        return playerCardMapper.playerCardToDTO(calculatePlayerCardTuningEngine(commandToPlayercard(command)));
    }


    public PlayerCardDTO calculatePlayerCardDTOTuningCornering(CalculateTuningCommand command) {
        return playerCardMapper.playerCardToDTO(calculatePlayerCardTuningCornering(commandToPlayercard(command)));
    }


    public <T extends PlayerCardDTO> PlayerCard commandToPlayercard(T command) {
        // a DataDTO miatt kell objectMappelni
        PlayerCardDTO playerCardDTO = objectMapper.convertValue(command, PlayerCardDTO.class);
        CardDTO cardDTO = objectMapper.convertValue(command.getCard().getValue(), CardDTO.class);
        playerCardDTO.getCard().setValue(cardDTO);

        return playerCardMapper.DTOToPlayerCard(playerCardDTO);
    }


    private PlayerCard tuningChassis(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.CHASSIS.getMultiplier();
        int tuningLevel = playerCard.getTunings().getChassis();
        CalculatedFields calculatedFields = playerCard.getCalculatedFields();

        calculatedFields.setAcceleration(playerCard.getCard().getAcceleration() - calcDoubleValue(tuningMultiplier, tuningLevel, playerCard.getCard().getAcceleration()));
        calculatedFields.setWeight(playerCard.getCard().getWeight() - calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getWeight()));
        calculatedFields.setTopSpeed(playerCard.getCard().getTopSpeed() + calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getTopSpeed()));

        return playerCard;
    }


    private PlayerCard tuningEngine(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.ENGINE.getMultiplier();
        int tuningLevel = playerCard.getTunings().getEngine();
        CalculatedFields calculatedFields = playerCard.getCalculatedFields();

        calculatedFields.setAcceleration(playerCard.getCard().getAcceleration() - calcDoubleValue(tuningMultiplier, tuningLevel, playerCard.getCard().getAcceleration()));
        calculatedFields.setPowerHP(playerCard.getCard().getPowerHP() + calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getPowerHP()));
        calculatedFields.setTopSpeed(playerCard.getCard().getTopSpeed() + calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getTopSpeed()));

        return playerCard;
    }


    private PlayerCard tuningCornering(PlayerCard playerCard) {

        double tuningMultiplier = ETuningMultiplier.CORNERING.getMultiplier();
        int tuningLevel = playerCard.getTunings().getCornering();
        CalculatedFields calculatedFields = playerCard.getCalculatedFields();

        calculatedFields.setWidth(playerCard.getCard().getWidth() + playerCard.getCalculatedFields().getWidth() + calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getWidth()));
        calculatedFields.setHeight(playerCard.getCard().getHeight() - calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getHeight()));
        calculatedFields.setGroundClearance(playerCard.getCard().getGroundClearance() - calcIntValue(tuningMultiplier, tuningLevel, playerCard.getCard().getGroundClearance()));

        return playerCard;
    }


    private int calcIntValue(double tuningMultiplier, int tuningLevel, int cardValue) {
        return (int)(cardValue * (tuningLevel * tuningMultiplier));
    }


    private double calcDoubleValue(double tuningMultiplier, int tuningLevel, double cardValue) {

        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        double result = cardValue * (tuningLevel * tuningMultiplier);

        return Double.parseDouble(new DecimalFormat("0.00", decimalFormatSymbols).format(result));
    }
}
