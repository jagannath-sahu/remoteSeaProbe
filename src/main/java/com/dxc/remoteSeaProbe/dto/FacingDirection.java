package com.dxc.remoteSeaProbe.dto;

public enum FacingDirection {
    NORTH, EAST, SOUTH, WEST;

    public FacingDirection left() {
        return values()[(ordinal() + 3) % 4];
    }

    public FacingDirection right() {
        return values()[(ordinal() + 1) % 4];
    }
}

