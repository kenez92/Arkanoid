package com.kenez92.client.game;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.Timer;
import com.kenez92.client.ball.BallSpeed;
import com.kenez92.client.sound.SoundEffect;
import com.kenez92.client.exception.LifeLostException;
import com.kenez92.client.brick.Brick;
import com.kenez92.client.timer.Clock;
import com.kenez92.client.player.PlayerInfo;
import com.kenez92.client.ball.BallService;
import com.kenez92.client.board.BoardService;
import com.kenez92.client.brick.BrickService;
import com.kenez92.client.racket.RacketService;
import com.kenez92.client.sound.SoundService;

import java.util.List;

import static com.kenez92.client.settings.BoardSettings.BOARD_REFRESH_TIME;
import static com.kenez92.client.settings.GameSettings.GAME_START_LEVEL;
import static com.kenez92.client.settings.GameSettings.GAME_TIME_IN_SECONDS;
import static com.kenez92.client.settings.GameSettings.LIFE_QUANTITY;

public class GameService {
    private final BoardService boardService;
    private final BallService ballService;
    private final RacketService racketService;
    private final BrickService brickService;
    private PlayerInfo playerInfo;
    private GameState gameState;
    private Clock clock;
    private final Timer timer;

    public GameService() {
        this.boardService = new BoardService();
        this.ballService = new BallService();
        this.racketService = new RacketService();
        this.brickService = new BrickService();
        racketService.addListeners(boardService.getCanvas());
        addClickEvent();
        this.timer = new Timer() {
            @Override
            public void run() {
                process();
            }
        };
    }

    public Canvas getGameBoard() {
        return boardService.getCanvas();
    }

    public void pauseGame() {
        if (gameState != GameState.LOST) {
            clock.stopCounting();
            gameState = GameState.PAUSE;
        }
    }

    public void playGame() {
        if (gameState == GameState.ABOUT_TO_START) {
            process();
        }
        if (gameState != GameState.PLAYING) {
            clock.startCounting();
            gameState = GameState.PLAYING;
        }
    }

    public void prepareNewGame(BallSpeed ballSpeed) {
        this.playerInfo = new PlayerInfo(LIFE_QUANTITY, ballSpeed, GAME_START_LEVEL);
        prepareNewGame(playerInfo);
    }

    private void prepareNewGame(PlayerInfo playerInfo) {
        this.ballService.setBallLevel(playerInfo.getBallSpeed());
        this.ballService.resetBallPositions();
        this.brickService.createBricks(playerInfo.getLevel());
        this.gameState = GameState.ABOUT_TO_START;
        this.clock = new Clock((playerInfo.getLevel() > 0 ? playerInfo.getLevel() : 1) * GAME_TIME_IN_SECONDS);
        process();
    }

    private void process() {
        this.timer.scheduleRepeating(BOARD_REFRESH_TIME);
        checkGameState(brickService.getBricks());
        if (gameState == GameState.PLAYING) {
            try {
                ballService.process(racketService.getRacket(), brickService.getBricks());
            } catch (LifeLostException ex) {
                lifeLost();
            }
        }
        if (gameState == GameState.LOST || gameState == GameState.WIN) {
            timer.cancel();
        }
        drawBoard();
    }

    private void checkGameState(List<Brick> bricks) {
        if (bricks.size() == 0) {
            this.gameState = GameState.WIN;
            SoundService.sound(SoundEffect.GAME_WIN);
            playerInfo.levelUp();
            prepareNewGame(playerInfo);
        }
        if (clock.getTime() <= 0) {
            this.gameState = GameState.LOST;
            SoundService.sound(SoundEffect.GAME_LOST);
        }
    }


    private void drawBoard() {
        if (playerInfo != null) {
            boardService.refreshBoard(
                    ballService.getBall(), racketService.getRacket(), brickService.getBricks(),
                    playerInfo.getLives(), clock.getActualTime(), gameState);
        }
    }

    private void lifeLost() {
        if (playerInfo.getLives() > 1) {
            playerInfo.lifeLost();
            clock.stopCounting();
            ballService.resetBallPositions();
            gameState = GameState.ABOUT_TO_START;
        } else {
            gameState = GameState.LOST;
        }
    }

    private void addClickEvent() {
        boardService.getCanvas().addClickHandler(event -> {
            if (this.gameState == GameState.ABOUT_TO_START || this.gameState == GameState.PAUSE) {
                this.gameState = GameState.PLAYING;
                clock.startCounting();
            }
        });
    }
}
