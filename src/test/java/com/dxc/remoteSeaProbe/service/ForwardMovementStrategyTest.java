package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.strategy.ForwardMovementStrategy;
import com.dxc.remoteSeaProbe.dto.FacingDirection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class ForwardMovementStrategyTest {

    private final ForwardMovementStrategy strategy = new ForwardMovementStrategy();

    @ParameterizedTest(name = "Facing {0} -> moves to {1}")
    @MethodSource("forwardCases")
    void forwardMovement(FacingDirection facing, Coordinates expected) {

        Coordinates start = new Coordinates(5, 5);

        Coordinates result = strategy.move(start, facing);

        assertEquals(expected.latitude(), result.latitude());
        assertEquals(expected.longitude(), result.longitude());
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> forwardCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.NORTH, new Coordinates(6, 5)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.SOUTH, new Coordinates(4, 5)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.EAST, new Coordinates(5, 6)),
                org.junit.jupiter.params.provider.Arguments.of(
                        FacingDirection.WEST, new Coordinates(5, 4))
        );
    }
}
