package com.dxc.remoteSeaProbe.strategy;

import com.dxc.remoteSeaProbe.dto.Command;
import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;
import org.springframework.stereotype.Component;

@Component
public class BackwardMovementStrategy implements MovementStrategy {

    @Override
    public boolean supports(Command command) {
        return command == Command.BACKWARD;
    }

    @Override
    public Coordinates move(Coordinates current, FacingDirection facing) {
        return switch (facing) {
            case NORTH -> new Coordinates(current.latitude() - 1, current.longitude());
            case SOUTH -> new Coordinates(current.latitude() + 1, current.longitude());
            case EAST  -> new Coordinates(current.latitude(), current.longitude() - 1);
            case WEST  -> new Coordinates(current.latitude(), current.longitude() + 1);
        };
    }
}

