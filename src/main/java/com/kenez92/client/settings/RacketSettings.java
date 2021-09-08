package com.kenez92.client.settings;

import static com.kenez92.client.settings.BallSettings.MAX_BALL_X_DIRECT;
import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;

public class RacketSettings {
    public static final double RACKET_WIDTH = 120;
    public static final double RACKET_HEIGHT = 30;
    public static final double RACKET_HALF_WIDTH = RACKET_WIDTH / 2;
    public static final double RACKET_COLLLISION_ANGLE = MAX_BALL_X_DIRECT / RACKET_HALF_WIDTH;

    public static final double RACKET_START_X_POSITION = BOARD_WIDTH / 2.0 - RACKET_HALF_WIDTH;
    public static final double RACKET_START_Y_POSITION = BOARD_HEIGHT * 0.9;
}
