package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("cards")
public class Card {

    @Id
    private String id;

    private String manufacturer;

    private String type;

    private Integer year;

    private String country;

    private Integer doors;

    private Integer cornering;

    private String body;

    private Integer seats;

    private String driveWheel;

    private String fuelType;

    private Integer fuelTankCapacity;

    private Integer engineCapacity;

    private Integer powerKW;

    private Integer powerHP;

    private Integer maxTorque;

    private Integer topSpeed;

    private Double acceleration;

    private Integer weight;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer groundClearance;

    private String abs;

    private String tractionControl;

    private String imageUrl;

    private String logoURL;

    private String carPageUrl;

    private String objectPositionHorizontal;

    private String objectPositionVertical;

    private String objectWidth;

    private String objectHeight;

    private Double gear1st;

    private Double gear2nd;

    private Double gear3rd;

    private Double gear4th;

    private Double gear5th;

    private Double gear6th;

    private Double finalDrive;
}
