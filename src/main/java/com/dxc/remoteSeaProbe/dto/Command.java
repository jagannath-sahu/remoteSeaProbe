package com.dxc.remoteSeaProbe.dto;

public enum Command {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    STAY;

    public static Command from(String value) {
        return Command.valueOf(value.trim().toUpperCase());
    }
}
