package com.games.cccgame.mapper;

import com.games.cccgame.dtos.GarageDTO;
import com.games.cccgame.models.Garage;
import org.modelmapper.ModelMapper;

public class GarageMapper {

    private ModelMapper modelMapper;

    public GarageDTO garageToDTO(Garage garage) {


        return modelMapper.map(garage, GarageDTO.class);
    }
}
