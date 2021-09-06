package com.kenez92.client.service;

import com.kenez92.client.model.Brick;
import com.kenez92.client.utils.Consts;

import java.util.LinkedList;
import java.util.List;

class BrickService {
    private List<Brick> bricks;

    BrickService() {
    }

    List<Brick> getBricks() {
        return bricks;
    }

    void createBricks(int level) {
        int maxBricksInX = Consts.MAX_BRICKS_IN_X;
        int maxBricksInY = getBricksQuantity(level);
        double freeSpaceBetweenBrickAndSideBorder = (Consts.BOARD_WIDTH - (maxBricksInX
                * (Consts.BRICK_WIDTH + Consts.FREE_SPACE_BETWEEN_BRICKS))) / 2;
        List<Brick> bricks = new LinkedList<>();
        for (int y = 0; y <= maxBricksInY; y++) {
            for (int x = 0; x < maxBricksInX; x++) {
                bricks.add(new Brick(getBrickLevel(level, y),
                        x * (Consts.BRICK_WIDTH + Consts.FREE_SPACE_BETWEEN_BRICKS)
                                + freeSpaceBetweenBrickAndSideBorder,
                        y * (Consts.BRICK_HEIGHT + Consts.FREE_SPACE_BETWEEN_BRICKS)));
            }
        }
        this.bricks = bricks;
    }

    private int getBricksQuantity(int level) {
        return Math.min(level, Consts.MAX_BRICKS_IN_Y);
    }

    private int getBrickLevel(int level, int y) {
        int result = level - y;
        return Math.min(result, Consts.MAX_BRICK_LEVEL);
    }
}
