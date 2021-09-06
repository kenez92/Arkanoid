package com.kenez92.client.service;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.kenez92.client.enums.GameState;
import com.kenez92.client.model.Ball;
import com.kenez92.client.model.Brick;
import com.kenez92.client.model.Racket;
import com.kenez92.client.utils.Consts;

import java.util.List;

class BoardService {
    private Canvas canvas;

    BoardService() {
        createBoard();
    }

    void refreshBoard(Ball ball, Racket racket, List<Brick> bricks, int livesQuantity,
                      String time, GameState gameState) {
        if (canvas != null) {
            if (gameState == GameState.PLAYING) {
                drawBoard(ball, racket, bricks, livesQuantity, time);
            } else if (gameState == GameState.LOST) {
                drawGameOver(canvas.getContext2d());
            } else if (gameState == GameState.WIN) {

                drawGameWin(canvas.getContext2d());
            } else if (gameState == GameState.ABOUT_TO_START || gameState == GameState.PAUSE) {
                drawBoard(ball, racket, bricks, livesQuantity, time);
                drawPlay(canvas.getContext2d());
            }
        }
    }

    Canvas getCanvas() {
        return canvas;
    }

    private void createBoard() {
        canvas = Canvas.createIfSupported();
        if (canvas == null) {
            FlowPanel flowPanel = new FlowPanel();
            flowPanel.add(new HTML("Sorry! Browser is not supported."));
        }
        canvas.setWidth(Consts.BOARD_WIDTH + "px");
        canvas.setHeight(Consts.BOARD_HEIGHT + "px");
        canvas.setCoordinateSpaceWidth(Consts.BOARD_WIDTH);
        canvas.setCoordinateSpaceHeight(Consts.BOARD_HEIGHT);
        canvas.getContext2d().fillRect(0, 0, Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
    }

    private void drawBoard(Ball ball, Racket racket, List<Brick> bricks, int livesQuantity, String time) {
        drawBackground(canvas.getContext2d());
        drawBall(canvas.getContext2d(), ball);
        drawRacket(canvas.getContext2d(), racket);
        drawBricks(canvas.getContext2d(), bricks);
        drawLivesQuantity(canvas.getContext2d(), livesQuantity);
        if (time != null) {
            drawTime(canvas.getContext2d(), time);
        }
    }

    private void drawBackground(Context2d context) {
        if (context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_BACKGROUND_PATH).getElement());
            context.drawImage(img, 0, 0, Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        }
    }

    private void drawBall(Context2d context, Ball ball) {
        if (ball != null && context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_BALL_PATH).getElement());
            context.drawImage(img, ball.getXPosition(), ball.getYPosition());
        }
    }

    private void drawRacket(Context2d context, Racket racket) {
        if (racket != null && context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_RACKET_PATH).getElement());
            context.drawImage(img, racket.getXPosition(), racket.getYPosition());
        }
    }

    private void drawBricks(Context2d context, List<Brick> bricks) {
        if (context != null) {
            for (Brick brick : bricks) {
                String imgPath;
                switch (brick.getBrickLevel()) {
                    case 0:
                        imgPath = Consts.IMAGE_BRICK_YELLOW_PATH;
                        break;
                    case 1:
                        imgPath = Consts.IMAGE_BRICK_BLUE_PATH;
                        break;
                    case 2:
                        imgPath = Consts.IMAGE_BRICK_RED_PATH;
                        break;
                    default:
                        imgPath = "";
                }
                ImageElement img = ImageElement.as(new Image(imgPath).getElement());
                context.drawImage(img, brick.getXPosition(), brick.getYPosition());
            }
        }
    }

    private void drawLivesQuantity(Context2d context, int livesQuantity) {
        if (context != null) {
            context.fillText("Lives: " + livesQuantity, 20, Consts.BOARD_HEIGHT - 30);
        }
    }

    private void drawTime(Context2d context, String time) {
        if (context != null) {
            context.fillText("Time left: " + time, Consts.BOARD_WIDTH - 100, Consts.BOARD_HEIGHT - 30);
        }
    }

    private void drawGameOver(Context2d context) {
        if (context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_GAME_OVER_PATH).getElement());
            context.drawImage(img, 0, 0, Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        }
    }

    private void drawGameWin(Context2d context) {
        if (context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_GAME_WIN_PATH).getElement());
            context.drawImage(img, 0, 0, Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        }
    }

    private void drawPlay(Context2d context) {
        if (context != null) {
            ImageElement img = ImageElement.as(new Image(Consts.IMAGE_PLAY_PATH).getElement());
            context.drawImage(img, Consts.PLAY_X_POSITION, Consts.PLAY_Y_POSITION, Consts.PLAY_RADIUS,
                    Consts.PLAY_RADIUS);
        }
    }
}
