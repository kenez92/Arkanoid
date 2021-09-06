package com.kenez92.client.model;

import com.kenez92.client.enums.BallSpeed;

public class PlayerInfo {
    private int lives;
    private final BallSpeed ballSpeed;
    private int level;

    public PlayerInfo(int lives, BallSpeed ballSpeed, int level) {
        this.lives = lives;
        this.ballSpeed = ballSpeed;
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public BallSpeed getBallSpeed() {
        return ballSpeed;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
    }

    public void lifeLost() {
        lives--;
    }
}
