package com.dxc.remoteSeaProbe.strategy;

import com.dxc.remoteSeaProbe.dto.Command;
import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;
import org.springframework.stereotype.Component;

@Component
public class TurnLeftStrategy implements MovementStrategy {

    @Override
    public boolean supports(Command command) {
        return command == Command.LEFT;
    }

    @Override
    public Coordinates move(Coordinates current, FacingDirection facing) {
        return current;
    }
}

