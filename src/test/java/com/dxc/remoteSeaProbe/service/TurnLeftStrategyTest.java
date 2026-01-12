package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.strategy.TurnLeftStrategy;
import org.junit.jupiter.api.Test;
import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnLeftStrategyTest {

    private final TurnLeftStrategy strategy = new TurnLeftStrategy();

    @Test
    void turnLeft_doesNotChangeCoordinates() {
        Coordinates start = new Coordinates(5, 5);

        Coordinates next = strategy.move(start, FacingDirection.NORTH);

        assertEquals(start, next);
    }
}
