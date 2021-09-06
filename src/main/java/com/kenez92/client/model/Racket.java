package com.kenez92.client.model;

import com.kenez92.client.utils.Consts;

public class Racket {
    private double xPosition;
    private double yPosition;

    public Racket() {
        this.xPosition = Consts.RACKET_START_X_POSITION;
        this.yPosition = Consts.RACKET_START_Y_POSITION;
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
