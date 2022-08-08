package com.games.cccgame.services;

import com.games.cccgame.command.GarageCommand;
import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.models.Card;
import com.games.cccgame.models.Garage;
import com.games.cccgame.repository.GarageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GarageService {

    private GarageRepository garageRepository;

    private ModelMapper modelMapper;

    public GarageDTO getGarage() {

        Garage garage = garageRepository.getGarageByOwnerId("62aba1a378c7a81bcc995a81");

        return modelMapper.map(garage, GarageDTO.class);
    }
}
