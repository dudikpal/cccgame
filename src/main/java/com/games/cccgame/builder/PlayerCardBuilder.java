package com.games.cccgame.builder;

import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;

import java.time.Instant;
import java.util.ArrayList;

public final class PlayerCardBuilder {
    private String id;
    private BaseCard baseCard;
    private Tunings tunings;
    private int weight;
    private int topSpeed;
    private double acceleration;
    private int powerHP;
    private int width;
    private int height;
    private int cornering;
    private int groundClearance;
    private int win;
    private int loss;
    private int value;
    private Instant createdAt;
    private Instant updatedAt;

    private PlayerCardBuilder() {
    }

    public static PlayerCardBuilder aPlayerCard() {
        return new PlayerCardBuilder();
    }

    public PlayerCardBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PlayerCardBuilder withBaseCard(BaseCard baseCard) {
        this.baseCard = baseCard;
        return this;
    }

    public PlayerCardBuilder withTunings(Tunings tunings) {
        this.tunings = tunings;
        return this;
    }

    public PlayerCardBuilder withWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public PlayerCardBuilder withTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
        return this;
    }

    public PlayerCardBuilder withAcceleration(double acceleration) {
        this.acceleration = acceleration;
        return this;
    }

    public PlayerCardBuilder withPowerHP(int powerHP) {
        this.powerHP = powerHP;
        return this;
    }

    public PlayerCardBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public PlayerCardBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public PlayerCardBuilder withCornering(int cornering) {
        this.cornering = cornering;
        return this;
    }

    public PlayerCardBuilder withGroundClearance(int groundClearance) {
        this.groundClearance = groundClearance;
        return this;
    }

    public PlayerCardBuilder withWin(int win) {
        this.win = win;
        return this;
    }

    public PlayerCardBuilder withLoss(int loss) {
        this.loss = loss;
        return this;
    }

    public PlayerCardBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public PlayerCardBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PlayerCardBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public PlayerCard build() {
        PlayerCard playerCard = new PlayerCard();
        playerCard.setId(id);
        playerCard.setBaseCard(baseCard);
        playerCard.setTunings(tunings);
        playerCard.setWeight(weight);
        playerCard.setTopSpeed(topSpeed);
        playerCard.setAcceleration(acceleration);
        playerCard.setPowerHP(powerHP);
        playerCard.setWidth(width);
        playerCard.setHeight(height);
        playerCard.setCornering(cornering);
        playerCard.setGroundClearance(groundClearance);
        playerCard.setWin(win);
        playerCard.setLoss(loss);
        playerCard.setValue(value);
        playerCard.setCreatedAt(createdAt);
        playerCard.setUpdatedAt(updatedAt);
        return playerCard;
    }
}
