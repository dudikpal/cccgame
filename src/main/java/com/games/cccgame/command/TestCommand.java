package com.games.cccgame.command;

import com.games.cccgame.CccGameApplication;
import com.games.cccgame.dtos.CalculatedFieldsDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.services.PlayerCardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class TestCommand {

    private PlayerCardService playerCardService;

    public void root() {

        PlayerCardDTO playerCard = playerCardService.getPlayerCardSkeleton();

        CalculatedFieldsDTO calculatedFieldsDTO = playerCard.getCalculatedFields();
            log.info(playerCard);
            calculatedFieldsDTO.getTopSpeed().setValue(1000);
            test(calculatedFieldsDTO);
            log.info(playerCard);
    }


    public void test(CalculatedFieldsDTO calculatedFields) {
        calculatedFields.getGroundClearance().setValue(500);
    }
}
