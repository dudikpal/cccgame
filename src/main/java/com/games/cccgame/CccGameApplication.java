package com.games.cccgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.games.cccgame.command.TestCommand;
import com.games.cccgame.dtos.CalculatedFieldsDTO;
import com.games.cccgame.dtos.PlayerCardDTO;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.mapper.PlayerCardMapper;
import com.games.cccgame.models.CalculatedFields;
import com.games.cccgame.models.ETuningMultiplier;
import com.games.cccgame.models.Garage;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.repository.PlayerCardRepository;
import com.games.cccgame.services.CardService;
import com.games.cccgame.services.GarageService;
import com.games.cccgame.services.PlayerCardService;
import com.sun.tools.javac.Main;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@SpringBootApplication
public class CccGameApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CccGameApplication.class, args);
/*        GarageService garageService = context.getBean(GarageService.class);
        PlayerCardService playerCardService = context.getBean(PlayerCardService.class);
        Garage garage = garageService.getRawGarage("63853bd2f28d8c7a30d0e4b3");
        PlayerCard  playerCard = garage.getPlayerCards().get(0);*/


        /*TestCommand testCommand = context.getBean(TestCommand.class);
        System.out.println(garageService.getAllGarageIds());

        testCommand.root();*/
        /*Thread newThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("In thread 1");

            }
        });
        newThread.start();
        System.out.println("kozepvonalakhatasara");
        Thread newThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Out from thread 2");
            }
        });

        newThread2.start();*/
    }



    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    }

}
