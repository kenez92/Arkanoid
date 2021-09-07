package com.kenez92.client.brick;

import java.util.LinkedList;
import java.util.List;

import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;
import static com.kenez92.client.settings.BrickSettings.BRICK_HEIGHT;
import static com.kenez92.client.settings.BrickSettings.BRICK_WIDTH;
import static com.kenez92.client.settings.BrickSettings.FREE_SPACE_BETWEEN_BRICKS;
import static com.kenez92.client.settings.BrickSettings.MAX_BRICKS_IN_X;
import static com.kenez92.client.settings.BrickSettings.MAX_BRICKS_IN_Y;
import static com.kenez92.client.settings.BrickSettings.MAX_BRICK_LEVEL;

public class BrickService {
    private List<Brick> bricks;

    public BrickService() {
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void createBricks(int level) {
        int maxBricksInX = MAX_BRICKS_IN_X;
        int maxBricksInY = getBricksQuantity(level);
        double freeSpaceBetweenBrickAndSideBorder = (BOARD_WIDTH - (maxBricksInX
                * (BRICK_WIDTH + FREE_SPACE_BETWEEN_BRICKS))) / 2;
        List<Brick> bricks = new LinkedList<>();
        for (int y = 0; y <= maxBricksInY; y++) {
            for (int x = 0; x < maxBricksInX; x++) {
                bricks.add(new Brick(getBrickLevel(level, y),
                        x * (BRICK_WIDTH + FREE_SPACE_BETWEEN_BRICKS)
                                + freeSpaceBetweenBrickAndSideBorder,
                        y * (BRICK_HEIGHT + FREE_SPACE_BETWEEN_BRICKS)));
            }
        }
        this.bricks = bricks;
    }

    private int getBricksQuantity(int level) {
        return Math.min(level, MAX_BRICKS_IN_Y);
    }

    private int getBrickLevel(int level, int y) {
        int result = level - y;
        return Math.min(result, MAX_BRICK_LEVEL);
    }
}
