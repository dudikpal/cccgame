package com.games.cccgame.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedFieldsDTO {

    private DataDTO weight = new DataDTO<Integer>("Weight", 0);

    private DataDTO topSpeed = new DataDTO<Integer>("TopSpeed", 0);

    private DataDTO acceleration = new DataDTO<Integer>("0-100 km/h (sec)", 0);

    private DataDTO powerHP = new DataDTO<Integer>("Power", 0);

    private DataDTO width = new DataDTO<Integer>("Width", 0);

    private DataDTO height = new DataDTO<Integer>("Height", 0);

    private DataDTO cornering = new DataDTO<Integer>("Cornering", 0);

    private DataDTO groundClearance = new DataDTO<Integer>("Ground clearance", 0);
}
