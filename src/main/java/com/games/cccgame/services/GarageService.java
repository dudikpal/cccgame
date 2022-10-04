package com.games.cccgame.services;

import com.games.cccgame.dtos.CardDTO;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
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

    private CardService cardService;

    @Autowired
    private GarageMapper garageMapper;

    public GarageDTO getGarage(String garageId) {

        Garage garage = garageRepository.findById(garageId).get();

        GarageDTO garageDTO = garageMapper.garageToDTO(garage);

        return garageDTO;
    }

    public GarageDTO createGarage() {

        Garage garage = new Garage(List.of(), LocalDate.now());
        //List<String> playerCards = new ArrayList <>();
        List<String> cardDTOS = new ArrayList <>();

        /*for ( CardDTO cardDTO : cardService.getCard(Optional.empty())) {
            //playerCards.add(playerCardService.createPlayerCard(cardDTO.getId().getValue().toString()).getId());
            cardDTOS.add(cardDTO.getId().getValue().toString());
        }*/

        cardDTOS.add(playerCardService.createPlayerCard("c_2-i-turbo-231hp-8302").getId().getValue().toString()/*.substring(2)*/);
        cardDTOS.add(playerCardService.createPlayerCard("c_0-tsi-evo-300hp-dsg-42357").getId().getValue().toString()/*.substring(2)*/);

        garage.setPlayerCards(cardDTOS);
        garageRepository.save(garage);

        return garageMapper.garageToDTO(garage);
    }
}
