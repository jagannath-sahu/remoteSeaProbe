package com.dxc.remoteSeaProbe.dto;

import lombok.Getter;

@Getter
public enum MovementDirection {

    LEFT(-2, 0),       // move west by 2 feet → longitude -2
    RIGHT(2, 0),       // move east by 2 feet → longitude +2
    FORWARD(0, 1),     // move north by 1 → latitude +1
    BACKWARD(0, -1),   // move south by -1 → latitude -1
    STAY(0, 0);        // no movement

    private final int deltaLongitude;
    private final int deltaLatitude;

    MovementDirection(int deltaLongitude, int deltaLatitude) {
        this.deltaLongitude = deltaLongitude;
        this.deltaLatitude = deltaLatitude;
    }

    public static MovementDirection fromString(String action) {
        if (action == null) {
            throw new IllegalArgumentException("Movement action cannot be null");
        }

        try {
            return MovementDirection.valueOf(action.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid movement action: " + action +
                                               ". Allowed values: left, right, forward, backward, stay");
        }
    }

    public Coordinates move(ProbeResponse probe) {
        return new Coordinates(probe.getLatitude() + deltaLatitude, probe.getLongitude() + deltaLongitude);
    }
}

