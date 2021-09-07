package com.kenez92.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.kenez92.client.game.GameController;
import com.kenez92.client.ball.BallSpeed;

public class ArkanoidApplication implements EntryPoint {
    private final GameController gameController;

    public ArkanoidApplication() {
        this.gameController = new GameController();
    }

    public void onModuleLoad() {
        ListBox listBox = new ListBox();
        listBox.addItem(BallSpeed.SLOW.toString());
        listBox.addItem(BallSpeed.EASY.toString());
        listBox.addItem(BallSpeed.NORMAL.toString());
        listBox.addItem(BallSpeed.DIFFICULTY.toString());
        listBox.addItem(BallSpeed.HARD.toString());

        Button start = new Button("New game");
        start.addClickHandler(clickEvent -> {
            gameController.prepareNewGame(BallSpeed.valueOf(listBox.getSelectedValue()));
        });
        Button pause = new Button("Pause");
        pause.addClickHandler(clickEvent -> {
            gameController.pauseGame();
        });

        Button play = new Button("Play");
        play.addClickHandler(clickEvent -> {
            gameController.playGame();
        });

        RootPanel.get("board").add(gameController.getBoard());
        RootPanel.get("buttons").add(start);
        RootPanel.get("buttons").add(pause);
        RootPanel.get("buttons").add(play);
        RootPanel.get("listBox").add(listBox);
    }
}
