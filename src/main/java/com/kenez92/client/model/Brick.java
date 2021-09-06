package com.kenez92.client.model;

import com.kenez92.client.enums.BrickSide;
import com.kenez92.client.utils.Consts;

import java.util.Arrays;

public class Brick {
    private int brickLevel;
    private final double xPosition;
    private final double yPosition;

    public Brick(int brickLevel, double xPosition, double yPosition) {
        this.brickLevel = brickLevel;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getBrickLevel() {
        return brickLevel;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void levelDown() {
        this.brickLevel--;
    }


    public BrickSide whichSideWasHit(Ball ball) {
        double toTheRightSide = Math.abs(this.xPosition + Consts.BRICK_WIDTH - ball.getXPosition());
        double toTheLeftSide = Math.abs(this.xPosition - (ball.getXPosition() + Consts.BALL_RADIUS));
        double toTheBottomSide = Math.abs(this.yPosition + Consts.BRICK_HEIGHT - ball.getYPosition());
        double toTheTopSide = Math.abs(this.yPosition - (ball.getYPosition() + Consts.BALL_RADIUS));

        double[] array = {toTheRightSide, toTheLeftSide, toTheBottomSide, toTheTopSide};
        Arrays.sort(array);
        double result = array[0];
        if (result == toTheRightSide && result == toTheTopSide) {
            return BrickSide.TOP_RIGHT_CORNER;
        } else if (result == toTheRightSide && result == toTheBottomSide) {
            return BrickSide.BOTTOM_RIGHT_CORNER;
        } else if (result == toTheLeftSide && result == toTheTopSide) {
            return BrickSide.TOP_LEFT_CORNER;
        } else if (result == toTheLeftSide && result == toTheBottomSide) {
            return BrickSide.BOTTOM_LEFT_CORNER;
        } else if (result == toTheBottomSide) {
            return BrickSide.BOTTOM;
        } else if (result == toTheTopSide) {
            return BrickSide.TOP;
        } else if (result == toTheLeftSide) {
            return BrickSide.LEFT;
        } else if (result == toTheRightSide) {
            return BrickSide.RIGHT;
        }
        return null;
    }
}
