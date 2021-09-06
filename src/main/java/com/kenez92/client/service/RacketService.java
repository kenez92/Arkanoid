package com.kenez92.client.service;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.kenez92.client.model.Racket;
import com.kenez92.client.utils.Consts;

class RacketService {
    private double xPosition;
    private final Racket racket;

    RacketService() {
        this.racket = new Racket();
    }

    Racket getRacket() {
        return racket;
    }

    void addListeners(Canvas canvas) {
        addMouseListener(canvas);
        canvas.addKeyDownHandler(this::arrowsControl);
    }

    private void addMouseListener(Canvas canvas) {
        canvas.addMouseMoveHandler(event -> {
            this.xPosition = event.getRelativeX(canvas.getElement());
            mouseMoving();
        });
    }

    private void mouseMoving() {
        racket.setXPosition(xPosition - Consts.RACKET_WIDTH / 2);
        if (racket.getXPosition() + Consts.RACKET_WIDTH >= Consts.BOARD_WIDTH) {
            racket.setXPosition(Consts.BOARD_WIDTH - Consts.RACKET_WIDTH);
        }
        if (racket.getXPosition() <= 0) {
            racket.setXPosition(0);
        }
    }

    private void arrowsControl(KeyDownEvent keyDownEvent) {
        double position;
        if (keyDownEvent.isLeftArrow() && racket.getXPosition() > 0) {
            position = racket.getXPosition() - 10;
            if (position < 0) {
                position = 0;
            }
            racket.setXPosition(position);
        }
        if (keyDownEvent.isRightArrow() && racket.getXPosition() < Consts.BOARD_WIDTH - Consts.RACKET_WIDTH) {
            position = racket.getXPosition() + 10;
            if (position > Consts.BOARD_WIDTH - Consts.RACKET_WIDTH) {
                position = Consts.BOARD_WIDTH - Consts.RACKET_WIDTH;
            }
            racket.setXPosition(position);
        }
    }
}
