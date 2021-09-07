package com.kenez92.client.racket;

import static com.kenez92.client.settings.RacketSettings.RACKET_START_X_POSITION;
import static com.kenez92.client.settings.RacketSettings.RACKET_START_Y_POSITION;

public class Racket {
    private double xPosition;
    private double yPosition;

    public Racket() {
        this.xPosition = RACKET_START_X_POSITION;
        this.yPosition = RACKET_START_Y_POSITION;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }
}
