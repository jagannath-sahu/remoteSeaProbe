package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.strategy.BackwardMovementStrategy;
import com.dxc.remoteSeaProbe.dto.FacingDirection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class BackwardMovementStrategyTest {

    private final BackwardMovementStrategy strategy = new BackwardMovementStrategy();

    @ParameterizedTest(name = "Facing {0} -> moves backward to {1}")
    @MethodSource("backwardCases")
    void backwardMovement(FacingDirection facing, Coordinates expected) {

        Coordinates start = new Coordinates(5, 5);

        Coordinates result = strategy.move(start, facing);

        assertEquals(expected.latitude(), result.latitude());
        assertEquals(expected.longitude(), result.longitude());
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> backwardCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.NORTH, new Coordinates(4, 5)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.SOUTH, new Coordinates(6, 5)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.EAST, new Coordinates(5, 4)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.WEST, new Coordinates(5, 6))
        );
    }
}

