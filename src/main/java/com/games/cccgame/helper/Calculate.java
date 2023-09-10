package com.games.cccgame.helper;

import com.games.cccgame.models.BaseCard;
import com.games.cccgame.models.PlayerCard;
import com.games.cccgame.models.Tunings;

import java.util.List;

public class Calculate {

    private double topSpeedWeight = 0.05;
    private double accelerationWeight = 0.1;
    private double powerHPWeight = 0.1;
    private double groundClearanceWeight = 0.1;
    private double driveWheelWeight = 0.1;
    private double widthWeight = 0.15;
    private double heightWeight = -0.15;
    private double weightWeight = -0.25;

    public static void playerCardValues(PlayerCard playerCard, BaseCard baseCard) {

            playerCard.setAcceleration(baseCard.getAcceleration());
            playerCard.setCornering(baseCard.getCornering());
            playerCard.setGroundClearance(baseCard.getGroundClearance());
            playerCard.setHeight(baseCard.getHeight());
            playerCard.setPowerHP(baseCard.getPowerHP());
            playerCard.setTopSpeed(baseCard.getTopSpeed());
            playerCard.setWeight(baseCard.getWeight());
            playerCard.setWidth(baseCard.getWidth());
    }

    public void baseCardCornering(BaseCard baseCard) {
        setDriveWheelWeight(baseCard.getDriveWheel());
        System.out.println(baseCard.getId());
        int corneringIndex = (int)((topSpeedWeight * baseCard.getTopSpeed())
            + (accelerationWeight * baseCard.getAcceleration())
            + (powerHPWeight * baseCard.getPowerHP())
            + (groundClearanceWeight * baseCard.getGroundClearance())
            + (driveWheelWeight * 1)
            + (widthWeight * baseCard.getWidth())
            - (heightWeight * baseCard.getHeight())
            - (weightWeight * baseCard.getWeight())
            );
        baseCard.setCornering(corneringIndex);
    }

    private void setDriveWheelWeight(String driveWheel) {
        if (driveWheel.equals("AWD")) {
            driveWheelWeight = 0.12;
            heightWeight = -0.13;
        }
    }

    public PlayerCard playerCardFields(PlayerCard playerCard) {

        BaseCard baseCard = playerCard.getBaseCard();
        Tunings tunings = playerCard.getTunings();
        playerCard.setWeight((int)(baseCard.getWeight() * weightMultiplier(tunings)));
        playerCard.setTopSpeed((int)(baseCard.getTopSpeed() * topSpeedMultiplier(tunings)));
        playerCard.setAcceleration(baseCard.getAcceleration() * accelerationMultiplier(tunings));
        playerCard.setPowerHP((int)(baseCard.getPowerHP() * powerHPMultiplier(tunings)));
        playerCard.setWidth((int)(baseCard.getWidth() * widthMultiplier(tunings)));
        playerCard.setHeight((int)(baseCard.getHeight() * heightMultiplier(tunings)));
        playerCard.setGroundClearance((int)(baseCard.getGroundClearance() * groundClearanceMultiplier(tunings)));
        playerCard.setCornering((int)(baseCard.getCornering() * corneringMultiplier(tunings)));

        return playerCard;
    }

    private double tuningMultiplier(List<Integer> multipliers) {
        float tuningMultiplier = 1;
        for (int multiplier : multipliers) {
            tuningMultiplier += (0.1 * multiplier);
        }

        return tuningMultiplier;
    }

    private double accelerationMultiplier(Tunings tunings) {
        return 1 - (0.1 * (tunings.getEngine() + tunings.getBody()));
    }

    private double weightMultiplier(Tunings tunings) {
        return 1 - (0.1 * tunings.getBody());
    }

    private double topSpeedMultiplier(Tunings tunings) {
        return 1 + (0.1 * (tunings.getEngine() + tunings.getBody()));
    }

    private double powerHPMultiplier(Tunings tunings) {
        return 1 + (0.1 * tunings.getEngine());
    }

    private double widthMultiplier(Tunings tunings) {
        return 1 + (0.1 * (tunings.getCornering() + tunings.getBody()));
    }

    private double heightMultiplier(Tunings tunings) {
        return 1 - (0.1 * (tunings.getCornering() + tunings.getBody()));
    }

    private double groundClearanceMultiplier(Tunings tunings) {
        return 1 - (0.1 * (tunings.getCornering() + tunings.getBody()));
    }

    private double corneringMultiplier(Tunings tunings) {
        return 1 + (0.1 * (tunings.getCornering() + tunings.getBody()));
    }
}
