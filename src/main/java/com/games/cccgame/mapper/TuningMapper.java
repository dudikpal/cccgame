package com.games.cccgame.mapper;

import com.games.cccgame.dtos.TuningsDTO;
import com.games.cccgame.models.Tunings;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class TuningMapper {

    public Tunings TuningsDTOToTunings(TuningsDTO tuningsDTO) {

        Tunings tunings = new Tunings();
        tunings.setEngine((int)tuningsDTO.getEngine().getValue());
        tunings.setChassis((int)tuningsDTO.getChassis().getValue());
        tunings.setCornering((int)tuningsDTO.getCornering().getValue());

        return tunings;
    }


    public TuningsDTO tuningsToTuningsDTO(Tunings tunings) {

        TuningsDTO tuningsDTO = new TuningsDTO();
        tuningsDTO.getEngine().setValue(tunings.getEngine());
        tuningsDTO.getChassis().setValue(tunings.getChassis());
        tuningsDTO.getCornering().setValue(tunings.getCornering());

        return tuningsDTO;
    }
}
