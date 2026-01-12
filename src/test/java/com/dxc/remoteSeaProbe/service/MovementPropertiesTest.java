package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.FacingDirection;
import com.dxc.remoteSeaProbe.strategy.BackwardMovementStrategy;
import com.dxc.remoteSeaProbe.strategy.ForwardMovementStrategy;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

class MovementPropertiesTest {

    private final ForwardMovementStrategy forward =
            new ForwardMovementStrategy();
    private final BackwardMovementStrategy backward =
            new BackwardMovementStrategy();

    @Property
    void forwardThenBackwardReturnsToSamePosition(
            @ForAll int x,
            @ForAll int y,
            @ForAll FacingDirection facing) {

        Coordinates start = new Coordinates(x, y);

        Coordinates afterForward =
                forward.move(start, facing);

        Coordinates afterBackward =
                backward.move(afterForward, facing);

        Assertions.assertThat(afterBackward)
                .isEqualTo(start);
    }
}

