package com.kenez92.client.ball;

import static com.kenez92.client.settings.BallSettings.BALL_RADIUS;
import static com.kenez92.client.settings.BallSettings.BALL_START_X_POSITION;
import static com.kenez92.client.settings.BallSettings.BALL_START_Y_POSITION;
import static com.kenez92.client.settings.BallSettings.DIFFICULTY_BALL_SPEED;
import static com.kenez92.client.settings.BallSettings.EASY_BALL_SPEED;
import static com.kenez92.client.settings.BallSettings.HARD_BALL_SPEED;
import static com.kenez92.client.settings.BallSettings.NORMAL_BALL_SPEED;
import static com.kenez92.client.settings.BallSettings.SLOW_BALL_SPEED;

public class Ball {
    private double xPosition;
    private double yPosition;
    private double xDirect;
    private double yDirect;
    private int ballSpeed;

    public Ball() {
        this.xPosition = BALL_START_X_POSITION;
        this.yPosition = BALL_START_Y_POSITION;
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

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getXDirect() {
        return xDirect;
    }

    public void setXDirect(double xDirect) {
        this.xDirect = xDirect;
    }

    public void move() {
        this.xPosition = xPosition + xDirect * ballSpeed;
        this.yPosition = yPosition + yDirect * ballSpeed;
    }

    public void setRightDirect() {
        if (this.xDirect < 0) {
            changeXDirect();
        }
    }

    public void setLeftDirect() {
        if (this.xDirect > 0) {
            changeXDirect();
        }
    }

    public void setBottomDirect() {
        if (this.yDirect < 0) {
            changeYDirect();
        }
    }

    public void changeYDirect() {
        this.yDirect = yDirect * -1;
    }

    public void changeXDirect() {
        this.xDirect = xDirect * -1;
    }

    public boolean isCollision(double minX, double maxX, double minY, double maxY) {
        return (this.xPosition + BALL_RADIUS >= minX
                && this.xPosition <= maxX)
                && (this.yPosition + BALL_RADIUS >= minY
                && this.yPosition <= maxY);
    }

    public void resetPosition() {
        this.xPosition = BALL_START_X_POSITION;
        this.yPosition = BALL_START_Y_POSITION;
        this.xDirect = 0;
        this.yDirect = -1;
    }

    public void setBallSpeed(BallSpeed level) {
        int ballSpeed;
        switch (level) {
            case EASY:
                ballSpeed = EASY_BALL_SPEED;
                break;
            case NORMAL:
                ballSpeed = NORMAL_BALL_SPEED;
                break;
            case DIFFICULTY:
                ballSpeed = DIFFICULTY_BALL_SPEED;
                break;
            case HARD:
                ballSpeed = HARD_BALL_SPEED;
                break;
            default:
                ballSpeed = SLOW_BALL_SPEED;
        }
        this.ballSpeed = ballSpeed;
    }
}
