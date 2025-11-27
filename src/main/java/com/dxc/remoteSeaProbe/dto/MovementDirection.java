package com.dxc.remoteSeaProbe.dto;

public enum MovementDirection {

    LEFT(2, 0, -2),        // move 2 feet west  → lon -2
    RIGHT(4, 0, 4),        // move 4 feet east → lon +4
    FORWARD(1, 1, 0),      // move 1 foot north → lat +1
    BACKWARD(1, -1, 0),    // move 1 foot south → lat -1
    STAY(0, 0, 0);         // no movement

    private final int feet;
    private final int latDelta;
    private final int lonDelta;

    MovementDirection(int feet, int latDelta, int lonDelta) {
        this.feet = feet;
        this.latDelta = latDelta;
        this.lonDelta = lonDelta;
    }

    public int getFeet() {
        return feet;
    }

    public int getLatDelta() {
        return latDelta;
    }

    public int getLonDelta() {
        return lonDelta;
    }
}
