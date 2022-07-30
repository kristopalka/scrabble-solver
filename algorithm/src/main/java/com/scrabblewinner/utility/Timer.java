package com.scrabblewinner.utility;

import static java.lang.System.currentTimeMillis;

public class Timer {
    long startTime;
    long measuredTime;

    public Timer() {
        this.startTime = currentTimeMillis();
        measuredTime = 0;
    }

    public float stop() {
        measuredTime = currentTimeMillis() - startTime;
        return measuredTimeInSeconds();
    }

    public float get() {
        return measuredTimeInSeconds();
    }

    private float measuredTimeInSeconds() {
        return ((float) measuredTime / 1000);
    }
}
