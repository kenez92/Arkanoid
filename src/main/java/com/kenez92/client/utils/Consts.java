package com.kenez92.client.utils;

public class Consts {

    public static final int BOARD_HEIGHT = 500;
    public static final int BOARD_WIDTH = 1000;

    public static final double RACKET_WIDTH = 120;
    public static final double RACKET_HEIGHT = 30;
    public static final double RACKET_COLLLISION_CORNER = 0.1108;
    public static final double RACKET_PIECES = 12;

    public static final double RACKET_START_X_POSITION = BOARD_WIDTH / 2.0 - RACKET_WIDTH / 2.0;
    public static final double RACKET_START_Y_POSITION = BOARD_HEIGHT * 0.9;

    public static final double BALL_RADIUS = 20;
    public static final double BALL_START_X_POSITION = (BOARD_WIDTH / 2.0) - (BALL_RADIUS / 2.0);
    public static final double BALL_START_Y_POSITION = BOARD_HEIGHT * 0.8;

    public static final int SLOW_BALL_SPEED = 3;
    public static final int EASY_BALL_SPEED = 5;
    public static final int NORMAL_BALL_SPEED = 7;
    public static final int DIFFICULTY_BALL_SPEED = 9;
    public static final int HARD_BALL_SPEED = 11;

    public static final double BRICK_WIDTH = 49;
    public static final double BRICK_HEIGHT = 29;
    public static final int FREE_SPACE_BETWEEN_BRICKS = 40;
    public static final int MAX_BRICK_LEVEL = 2;
    public static final int SPACE_BETWEEN_UP_BORDER_AND_BRICKS = (int) (BOARD_HEIGHT * 0.1);
    public static final int MAX_BRICKS_IN_X = (int) Math.floor(Consts.BOARD_WIDTH / (Consts.BRICK_WIDTH + FREE_SPACE_BETWEEN_BRICKS));
    public static final int MAX_BRICKS_IN_Y = (int) Math.floor((BOARD_HEIGHT - (BOARD_HEIGHT + FREE_SPACE_BETWEEN_BRICKS
            - BALL_START_Y_POSITION) - SPACE_BETWEEN_UP_BORDER_AND_BRICKS) / BRICK_HEIGHT);

    public static final int GAME_START_LEVEL = 1;

    public static final int BOARD_REFRESH_TIME = 30;

    public static final long GAME_TIME_IN_SECONDS = 180;
    public static final int LIFE_QUANTITY = 3;

    public static final double PLAY_RADIUS = BOARD_HEIGHT < BOARD_WIDTH ? BOARD_HEIGHT / 2 : BOARD_WIDTH / 2;
    public static final double PLAY_X_POSITION = (BOARD_WIDTH - PLAY_RADIUS) / 2;
    public static final double PLAY_Y_POSITION = (BOARD_HEIGHT - PLAY_RADIUS) / 2;

    public static final String SOUND_COLLISION_BORDER_PATH = "sounds/collision_border.mp3";
    public static final String SOUND_COLLISION_RACKET_PATH = "sounds/collision_racket.mp3";
    public static final String SOUND_COLLISION_BRICK_PATH = "sounds/collision_brick.mp3";
    public static final String SOUND_GAME_OVER_PATH = "sounds/game_over.mp3";
    public static final String SOUND_GAME_WIN_PATH = "sounds/game_win.mp3";
    public static final String SOUND_LIFE_LOST_PATH = "sounds/life_lost.mp3";

    public static final String IMAGE_BACKGROUND_PATH = "img/background.jpg";
    public static final String IMAGE_BALL_PATH = "img/ball.png";
    public static final String IMAGE_RACKET_PATH = "img/racket.png";
    public static final String IMAGE_BRICK_YELLOW_PATH = "img/brick_yellow.png";
    public static final String IMAGE_BRICK_BLUE_PATH = "img/brick_blue.png";
    public static final String IMAGE_BRICK_RED_PATH = "img/brick_red.png";
    public static final String IMAGE_GAME_OVER_PATH = "img/game_over.png";
    public static final String IMAGE_GAME_WIN_PATH = "img/game_win.png";
    public static final String IMAGE_PLAY_PATH = "img/play.png";
}
