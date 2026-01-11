package com.dxc.remoteSeaProbe.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovementResult {
    private Coordinates coordinates;
    private FacingDirection facing;
}
