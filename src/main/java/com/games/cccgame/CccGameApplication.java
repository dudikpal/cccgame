package com.games.cccgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.games.cccgame.mapper.GarageMapper;
import com.games.cccgame.models.ETuningMultiplier;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class CccGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(CccGameApplication.class, args);

        /*ETuningMultiplier[] e = ETuningMultiplier.values();

        for (ETuningMultiplier etm : e) {
            System.out.printf("Name: %s, Value: %s%n", etm.name(), etm.getMultiplier());
        }*/
            /*Arrays.stream(e).forEach(entry -> {
                System.out.println(entry.name() + entry.getMultiplier());
            });*/


//        System.out.println(Arrays.stream(e).collect(Collectors.toMap(ETuningMultiplier::name, ETuningMultiplier::getMultiplier)));
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return  modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    }

}
