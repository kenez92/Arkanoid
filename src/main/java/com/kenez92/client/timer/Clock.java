package com.kenez92.client.timer;

import com.google.gwt.user.client.Timer;

public class Clock {
    private final Timer timer;
    private long time;

    private final int ONE_SECONDS_IN_MILLISECONDS = 1000;

    public Clock(long time) {
        this.time = time;
        this.timer = new Timer() {
            @Override
            public void run() {
                countdown();
            }
        };
    }

    public void stopCounting() {
        timer.cancel();
    }

    public void startCounting() {
        timer.scheduleRepeating(ONE_SECONDS_IN_MILLISECONDS);
    }

    public long getTime() {
        return time;
    }

    public String getActualTime() {
        long minutes = this.time / 60;
        long seconds = this.time % 60;
        String secondsAsString = seconds >= 10 ? String.valueOf(seconds) : "0" + seconds;
        return minutes + ":" + secondsAsString;
    }

    private void countdown() {
        time--;
    }
}
