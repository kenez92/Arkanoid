package com.kenez92.client.ball;

import com.kenez92.client.brick.Brick;
import com.kenez92.client.brick.BrickSide;
import com.kenez92.client.exception.LifeLostException;
import com.kenez92.client.racket.Racket;
import com.kenez92.client.sound.SoundEffect;
import com.kenez92.client.sound.SoundService;

import java.util.List;

import static com.kenez92.client.settings.BallSettings.BALL_RADIUS;
import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;
import static com.kenez92.client.settings.BrickSettings.BRICK_HEIGHT;
import static com.kenez92.client.settings.BrickSettings.BRICK_WIDTH;
import static com.kenez92.client.settings.RacketSettings.RACKET_COLLLISION_ANGLE;
import static com.kenez92.client.settings.RacketSettings.RACKET_HALF_WIDTH;
import static com.kenez92.client.settings.RacketSettings.RACKET_HEIGHT;
import static com.kenez92.client.settings.RacketSettings.RACKET_WIDTH;

public class BallService {
    private final Ball ball;

    public BallService() {
        this.ball = new Ball();
    }

    public void process(Racket racket, List<Brick> bricks) {
        setBallXDirection(racket, bricks);
    }


    public void setBallLevel(BallSpeed ballSpeed) {
        ball.setBallSpeed(ballSpeed);
    }

    public Ball getBall() {
        return ball;
    }

    public void resetBallPositions() {
        ball.resetPosition();
    }

    private void setBallXDirection(Racket racket, List<Brick> bricks) {
        if (checkBorderCollision()) {
            SoundService.sound(SoundEffect.COLLISION_BORDER);
        } else if (checkRacketCollision(racket)) {
            SoundService.sound(SoundEffect.COLLISION_RACKET);
        } else if (checkBrickCollision(bricks)) {
            SoundService.sound(SoundEffect.COLLISION_BRICK);
        }
        ball.move();
    }

    private boolean checkBorderCollision() {
        if (ball.getXPosition() <= 0) {
            ball.setRightDirect();
            return true;
        } else if (ball.getXPosition() + BALL_RADIUS >= BOARD_WIDTH) {
            ball.setLeftDirect();
            return true;
        } else if (ball.getYPosition() <= 0) {
            ball.setBottomDirect();
            return true;
        }
        if (ball.getYPosition() >= BOARD_HEIGHT - BALL_RADIUS) {
            SoundService.sound(SoundEffect.LIFE_LOST);
            throw new LifeLostException();
        }
        return false;
    }

    private boolean checkRacketCollision(Racket racket) {
        if (ball.isCollision(
                racket.getXPosition(),
                racket.getXPosition() + RACKET_WIDTH,
                racket.getYPosition(),
                racket.getYPosition() + RACKET_HEIGHT)) {
            ball.setXDirect(getAngle(ball.getXPosition(), racket.getXPosition()));
            ball.changeYDirect();
            ball.setYPosition(racket.getYPosition() - BALL_RADIUS - 1);
            return true;
        }
        return false;
    }

    private boolean checkBrickCollision(List<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (ball.isCollision(brick.getXPosition(), brick.getXPosition() + BRICK_WIDTH,
                    brick.getYPosition(), +brick.getYPosition() + BRICK_HEIGHT)) {
                BrickSide brickSide = brick.whichSideWasHit(ball);
                changeDirectDependsOnBrickSide(brickSide, brick.getXPosition(), brick.getYPosition());
                brick.levelDown();
                if (brick.getBrickLevel() < 0) {
                    bricks.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    double getAngle(double xBallPosition, double xRacketPosition) {
        double xCollision = xBallPosition + (BALL_RADIUS / 2);
        double distanceFromRacketCenter = xCollision - xRacketPosition - RACKET_HALF_WIDTH;
        return RACKET_COLLLISION_ANGLE * distanceFromRacketCenter;
    }

    public void changeDirectDependsOnBrickSide(BrickSide brickSide, double brickXPosition,
                                               double brickYPosition) {
        if (brickSide != null) {
            switch (brickSide) {
                case TOP:
                    ball.setYPosition(brickYPosition - BALL_RADIUS - 1);
                    ball.changeYDirect();
                    break;
                case LEFT:
                    ball.setXPosition(brickXPosition - BALL_RADIUS - 1);
                    ball.changeXDirect();
                    break;
                case RIGHT:
                    ball.setXPosition(brickXPosition + BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    break;
                case BOTTOM:
                    ball.setYPosition(brickYPosition + BRICK_HEIGHT + 1);
                    ball.changeYDirect();
                    break;
                case TOP_LEFT_CORNER:
                    ball.setYPosition(brickYPosition - BALL_RADIUS - 1);
                    ball.setXPosition(brickXPosition - BALL_RADIUS - 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case TOP_RIGHT_CORNER:
                    ball.setYPosition(brickYPosition - BALL_RADIUS - 1);
                    ball.setXPosition(brickXPosition + BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case BOTTOM_LEFT_CORNER:
                    ball.setYPosition(brickYPosition + BRICK_HEIGHT + 1);
                    ball.setXPosition(brickXPosition - BALL_RADIUS - 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case BOTTOM_RIGHT_CORNER:
                    ball.setYPosition(brickYPosition + BRICK_HEIGHT + 1);
                    ball.setXPosition(brickXPosition + BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
            }
        }
    }
}
