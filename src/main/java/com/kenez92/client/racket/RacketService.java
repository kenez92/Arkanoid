package com.kenez92.client.racket;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.KeyDownEvent;

import static com.kenez92.client.settings.BoardSettings.BOARD_WIDTH;
import static com.kenez92.client.settings.RacketSettings.RACKET_HALF_WIDTH;
import static com.kenez92.client.settings.RacketSettings.RACKET_WIDTH;

public class RacketService {
    private double xPosition;
    private final Racket racket;

    public RacketService() {
        this.racket = new Racket();
    }

    public Racket getRacket() {
        return racket;
    }

    public void addListeners(Canvas canvas) {
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
        racket.setXPosition(xPosition - RACKET_HALF_WIDTH);
        if (racket.getXPosition() + RACKET_WIDTH >= BOARD_WIDTH) {
            racket.setXPosition(BOARD_WIDTH - RACKET_WIDTH);
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
        if (keyDownEvent.isRightArrow() && racket.getXPosition() < BOARD_WIDTH - RACKET_WIDTH) {
            position = racket.getXPosition() + 10;
            if (position > BOARD_WIDTH - RACKET_WIDTH) {
                position = BOARD_WIDTH - RACKET_WIDTH;
            }
            racket.setXPosition(position);
        }
    }
}
