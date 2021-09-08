package com.kenez92.client.settings;

import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;

public class BallSettings {
    public static final double BALL_RADIUS = 20;
    public static final double BALL_START_X_POSITION = (BOARD_WIDTH / 2.0) - (BALL_RADIUS / 2.0);
    public static final double BALL_START_Y_POSITION = BOARD_HEIGHT * 0.8;
    public static final double MAX_BALL_X_DIRECT = 2;
    public static final int SLOW_BALL_SPEED = 3;
    public static final int EASY_BALL_SPEED = 5;
    public static final int NORMAL_BALL_SPEED = 7;
    public static final int DIFFICULTY_BALL_SPEED = 9;
    public static final int HARD_BALL_SPEED = 11;
}
