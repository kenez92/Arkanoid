package com.kenez92.client.game;

import com.google.gwt.canvas.client.Canvas;
import com.kenez92.client.ball.BallSpeed;


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
