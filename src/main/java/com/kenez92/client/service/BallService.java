package com.kenez92.client.service;

import com.kenez92.client.enums.BallSpeed;
import com.kenez92.client.enums.BrickSide;
import com.kenez92.client.enums.SoundEffect;
import com.kenez92.client.exception.LifeLostException;
import com.kenez92.client.model.Ball;
import com.kenez92.client.model.Brick;
import com.kenez92.client.model.Racket;
import com.kenez92.client.utils.Consts;
import com.kenez92.client.utils.SoundUtil;

import java.util.List;

class BallService {
    private final Ball ball;

    public BallService() {
        this.ball = new Ball();
    }

    public void process(Racket racket, List<Brick> bricks) {
        setBallXDirection(racket, bricks);
    }


    void setBallLevel(BallSpeed ballSpeed) {
        ball.setBallSpeed(ballSpeed);
    }

    Ball getBall() {
        return ball;
    }

    void resetBallPositions() {
        ball.resetPosition();
    }

    private void setBallXDirection(Racket racket, List<Brick> bricks) {
        if (checkBorderCollision()) {
            SoundUtil.sound(SoundEffect.COLLISION_BORDER);
        } else if (checkRacketCollision(racket)) {
            SoundUtil.sound(SoundEffect.COLLISION_RACKET);
        } else if (checkBrickCollision(bricks)) {
            SoundUtil.sound(SoundEffect.COLLISION_BRICK);
        }
        ball.move();
    }

    private boolean checkBorderCollision() {
        if (ball.getXPosition() <= 0) {
            ball.setRightDirect();
            return true;
        } else if (ball.getXPosition() + Consts.BALL_RADIUS >= Consts.BOARD_WIDTH) {
            ball.setLeftDirect();
            return true;
        } else if (ball.getYPosition() <= 0) {
            ball.setBottomDirect();
            return true;
        }
        if (ball.getYPosition() >= Consts.BOARD_HEIGHT - Consts.BALL_RADIUS) {
            SoundUtil.sound(SoundEffect.LIFE_LOST);
            throw new LifeLostException();
        }
        return false;
    }

    private boolean checkRacketCollision(Racket racket) {
        if (ball.isCollision(
                racket.getXPosition(),
                racket.getXPosition() + Consts.RACKET_WIDTH,
                racket.getYPosition(),
                racket.getYPosition() + Consts.RACKET_HEIGHT)) {
            ball.setXDirect(getCorner(ball.getXPosition(), racket.getXPosition(), ball.getXDirect()));
            ball.changeYDirect();
            ball.setYPosition(racket.getYPosition() - Consts.BALL_RADIUS - 1);
            return true;
        }
        return false;
    }

    private boolean checkBrickCollision(List<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (ball.isCollision(brick.getXPosition(), brick.getXPosition() + Consts.BRICK_WIDTH,
                    brick.getYPosition(), +brick.getYPosition() + Consts.BRICK_HEIGHT)) {
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

    private double getCorner(double xBallPosition, double xRacketPosition, double xDirect) {
        xBallPosition = xBallPosition + (Consts.BALL_RADIUS / 2);
        double onePieceOfRacket = Consts.RACKET_PIECES;
        double result = xBallPosition - xRacketPosition;
        int pieceOfRacket = (int) (result / onePieceOfRacket);
        if (xDirect > 0) {
            return Consts.RACKET_COLLLISION_CORNER * pieceOfRacket;
        } else {
            return Consts.RACKET_COLLLISION_CORNER * (Consts.RACKET_PIECES - pieceOfRacket) * -1;
        }
    }

    public void changeDirectDependsOnBrickSide(BrickSide brickSide, double brickXPosition,
                                               double brickYPosition) {
        if (brickSide != null) {
            switch (brickSide) {
                case TOP:
                    ball.setYPosition(brickYPosition - Consts.BALL_RADIUS - 1);
                    ball.changeYDirect();
                    break;
                case LEFT:
                    ball.setXPosition(brickXPosition - Consts.BALL_RADIUS - 1);
                    ball.changeXDirect();
                    break;
                case RIGHT:
                    ball.setXPosition(brickXPosition + Consts.BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    break;
                case BOTTOM:
                    ball.setYPosition(brickYPosition + Consts.BRICK_HEIGHT + 1);
                    ball.changeYDirect();
                    break;
                case TOP_LEFT_CORNER:
                    ball.setYPosition(brickYPosition - Consts.BALL_RADIUS - 1);
                    ball.setXPosition(brickXPosition - Consts.BALL_RADIUS - 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case TOP_RIGHT_CORNER:
                    ball.setYPosition(brickYPosition - Consts.BALL_RADIUS - 1);
                    ball.setXPosition(brickXPosition + Consts.BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case BOTTOM_LEFT_CORNER:
                    ball.setYPosition(brickYPosition + Consts.BRICK_HEIGHT + 1);
                    ball.setXPosition(brickXPosition - Consts.BALL_RADIUS - 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
                case BOTTOM_RIGHT_CORNER:
                    ball.setYPosition(brickYPosition + Consts.BRICK_HEIGHT + 1);
                    ball.setXPosition(brickXPosition + Consts.BRICK_WIDTH + 1);
                    ball.changeXDirect();
                    ball.changeYDirect();
                    break;
            }
        }
    }
}
