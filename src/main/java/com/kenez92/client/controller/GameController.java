package com.kenez92.client.controller;

import com.google.gwt.canvas.client.Canvas;
import com.kenez92.client.enums.BallSpeed;
import com.kenez92.client.service.GameService;
import com.kenez92.client.utils.Consts;


public class GameController {
    private final GameService gameService;

    public GameController() {
        this.gameService = new GameService();
    }

    public Canvas getBoard() {
        return gameService.getGameBoard();
    }

    public void prepareNewGame(BallSpeed ballSpeed) {
        gameService.prepareNewGame(ballSpeed);
    }

    public void playGame() {
        gameService.playGame();

    }

    public void pauseGame() {
        gameService.pauseGame();
    }
}
