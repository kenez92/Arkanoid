package com.kenez92.client.settings;

import static com.kenez92.client.settings.BallSettings.BALL_START_Y_POSITION;
import static com.kenez92.client.settings.BoardSettings.BOARD_HEIGHT;
import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;

public class BrickSettings {
    public static final double BRICK_WIDTH = 49;
    public static final double BRICK_HEIGHT = 29;

    public static final int FREE_SPACE_BETWEEN_BRICKS = 40;
    public static final int MAX_BRICK_LEVEL = 2;

    public static final int SPACE_BETWEEN_UP_BORDER_AND_BRICKS = (int) (BOARD_HEIGHT * 0.1);

    public static final int MAX_BRICKS_IN_X = (int) Math.floor(BOARD_WIDTH / (BRICK_WIDTH + FREE_SPACE_BETWEEN_BRICKS));
    public static final int MAX_BRICKS_IN_Y = (int) Math.floor((BOARD_HEIGHT - (BOARD_HEIGHT + FREE_SPACE_BETWEEN_BRICKS
            - BALL_START_Y_POSITION) - SPACE_BETWEEN_UP_BORDER_AND_BRICKS) / BRICK_HEIGHT);
}
