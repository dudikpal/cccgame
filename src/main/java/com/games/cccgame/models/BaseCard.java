package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("base_cards")
public class BaseCard {

    @Id
    private String id;

    private String manufacturer;

    private String type;

    private String country;

    private String body;

    private String driveWheel;

    private String fuelType;

    private String abs;

    private String tractionControl;

    private String imageUrl;

    private String logoURL;

    private String carPageUrl;

    private String objectPositionHorizontal;

    private String objectPositionVertical;

    private String objectWidth;

    private String objectHeight;

    private Integer year;

    private Integer doors;

    private Integer cornering;

    private Integer seats;

    private Integer fuelTankCapacity;

    private Integer engineCapacity;

    private Integer powerKW;

    private Integer powerHP;

    private Integer maxTorque;

    private Integer topSpeed;

    private Integer weight;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer groundClearance;

    private Double acceleration;

    private Double gear1st;

    private Double gear2nd;

    private Double gear3rd;

    private Double gear4th;

    private Double gear5th;

    private Double gear6th;

    private Double finalDrive;

    private Instant createdAt;

    private Instant updatedAt;
}
