package com.kenez92.client.settings;

import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;

public class RacketSettings {
    public static final double RACKET_WIDTH = 120;
    public static final double RACKET_HEIGHT = 30;
    public static final double RACKET_COLLLISION_CORNER = 0.1108;
    public static final double RACKET_PIECES = 12;

    public static final double RACKET_START_X_POSITION = BOARD_WIDTH / 2.0 - RACKET_WIDTH / 2.0;
    public static final double RACKET_START_Y_POSITION = BOARD_HEIGHT * 0.9;
}
