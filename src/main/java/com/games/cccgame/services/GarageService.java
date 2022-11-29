package com.games.cccgame.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.cccgame.command.UpgradePlayerCardCommand;
import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.CardMapper;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;
import com.games.cccgame.repository.GarageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GarageService {

    private GarageRepository garageRepository;

    private PlayerCardService playerCardService;

    private PlayerCardMapper playerCardMapper;

    private ObjectMapper objectMapper;

    @Autowired
    private GarageMapper garageMapper;


    public GarageDTO getGarage(String garageId) {

        Garage garage = garageRepository.findById(garageId).get();
        GarageDTO garageDTO = garageMapper.garageToDTO(garage);

        return garageDTO;
    }


    public GarageDTO createGarage() {

        Garage garage = new Garage(List.of(), LocalDate.now());
        List <PlayerCard> playerCards = new ArrayList <>();

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


    public void updateGarage(String garageId, UpgradePlayerCardCommand command) {

        PlayerCardDTO upgradedCardDTO = objectMapper.convertValue(command, PlayerCardDTO.class);
        CardDTO cardDTO = objectMapper.convertValue(command.getCard().getValue(), CardDTO.class);
        upgradedCardDTO.getCard().setValue(cardDTO);
        GarageDTO garageDTO = getGarage(garageId);
        PlayerCardDTO originalPlayerCardDTO = garageDTO.getPlayerCards().stream()
            .filter(card -> card.getId().getValue().equals(upgradedCardDTO.getId().getValue()))
                .findFirst()
            .get();

        garageDTO.getPlayerCards().set(garageDTO.getPlayerCards().indexOf(originalPlayerCardDTO), upgradedCardDTO);
        garageRepository.save(garageMapper.dToToGarage(garageDTO));
        System.out.println("Garage updated");
    }
}
