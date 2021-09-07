package com.kenez92.client.settings;

import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;

public class ImageSettings {
    public static final double PLAY_RADIUS = BOARD_HEIGHT < BOARD_WIDTH ? BOARD_HEIGHT / 2 : BOARD_WIDTH / 2;
    public static final double PLAY_X_POSITION = (BOARD_WIDTH - PLAY_RADIUS) / 2;
    public static final double PLAY_Y_POSITION = (BOARD_HEIGHT - PLAY_RADIUS) / 2;

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
