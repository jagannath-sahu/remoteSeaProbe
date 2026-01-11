package com.dxc.remoteSeaProbe.strategy;

import com.dxc.remoteSeaProbe.dto.Command;
import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;

public interface MovementStrategy {
    boolean supports(Command command);
    Coordinates move(Coordinates current, FacingDirection facing);
}

