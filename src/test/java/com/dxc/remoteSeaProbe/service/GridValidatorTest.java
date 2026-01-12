package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.validation.GridValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GridValidatorTest {

    private final GridValidator validator = new GridValidator();

    @ParameterizedTest(name = "Invalid position: {0}")
    @MethodSource("invalidPositions")
    void shouldRejectInvalidGridPositions(Coordinates coordinates) {

        IllegalStateException ex =
                assertThrows(IllegalStateException.class,
                        () -> validator.validate(coordinates));

        assertNotNull(ex.getMessage());
    }

    static Stream<Arguments> invalidPositions() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(-1, 5)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(5, -1)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(101, 5)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(5, 101)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(5, 5)),   // obstacle
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(10, 3))  // obstacle
        );
    }

    @ParameterizedTest
    @MethodSource("validPositions")
    void shouldAllowValidPositions(Coordinates coordinates) {
        assertDoesNotThrow(() -> validator.validate(coordinates));
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> validPositions() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(0, 0)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(1, 1)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(100, 100)),
                org.junit.jupiter.params.provider.Arguments.of(new Coordinates(50, 50))
        );
    }

}

