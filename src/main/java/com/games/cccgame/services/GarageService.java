package com.games.cccgame.services;

import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.models.Garage;
import com.games.cccgame.repository.GarageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class GarageService {

    private GarageRepository garageRepository;

    private ModelMapper modelMapper;

    public GarageDTO getGarage(String garageId) {

        Garage garage = garageRepository.findById(garageId).get();

        return modelMapper.map(garage, GarageDTO.class);
    }

    public String getGarageId(String ownerId) {

        return garageRepository.getGarageByOwnerId(ownerId).getId();
    }

    public GarageDTO createGarage() {

        Garage garage = new Garage(List.of());

        return modelMapper.map(garageRepository.save(garage), GarageDTO.class);
    }
}
