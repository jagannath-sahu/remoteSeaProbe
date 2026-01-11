package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Command;
import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;
import com.dxc.remoteSeaProbe.dto.MovementResult;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.strategy.MovementStrategy;
import com.dxc.remoteSeaProbe.validation.GridValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProbeMovementEngine {

    private final List<MovementStrategy> strategies;
    private final GridValidator gridValidator;

    public ProbeMovementEngine(List<MovementStrategy> strategies,
                               GridValidator gridValidator) {
        this.strategies = strategies;
        this.gridValidator = gridValidator;
    }

    public MovementResult execute(RemoteSeaProbe probe, Command command) {

        Coordinates current = currentCoordinates(probe);

        MovementStrategy strategy = strategies.stream()
                .filter(s -> s.supports(command))
                .findFirst()
                .orElseThrow();

        Coordinates next = strategy.move(current, probe.getDirectionFacing());

        gridValidator.validate(next);

        FacingDirection newFacing = updateFacing(probe, command);

        return MovementResult.builder()
                .coordinates(next)
                .facing(newFacing)
                .build();
    }

    private Coordinates currentCoordinates(RemoteSeaProbe probe) {
        return new Coordinates(
                probe.getInitialLatitude(),
                probe.getInitialLongitude()
        );
    }

    private FacingDirection updateFacing(RemoteSeaProbe probe, Command command) {
        return switch (command) {
            case LEFT -> probe.getDirectionFacing().left();
            case RIGHT -> probe.getDirectionFacing().right();
            default -> probe.getDirectionFacing();
        };
    }
}

