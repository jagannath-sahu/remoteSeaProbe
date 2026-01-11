package com.dxc.remoteSeaProbe.validation;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GridValidator {

    private static final int MAX_X = 100;
    private static final int MAX_Y = 100;

    private static final Set<Coordinates> OBSTACLES = Set.of(
            new Coordinates(5, 5),
            new Coordinates(10, 3)
    );

    public void validate(Coordinates next) {
        if (next.latitude() < 0 || next.longitude() < 0 ||
                next.latitude() > MAX_Y || next.longitude() > MAX_X) {
            throw new IllegalStateException("Outside grid");
        }

        if (OBSTACLES.contains(next)) {
            throw new IllegalStateException("Obstacle detected");
        }
    }
}

