package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.*;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.persistence.repo.ProbeTravelHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.dxc.remoteSeaProbe.dto.MovementResult;
import com.dxc.remoteSeaProbe.persistence.entity.ProbeTravelHistory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.InOrder;

@ExtendWith(MockitoExtension.class)
class ProbeTravelHistoryServiceTest {

    @Mock
    private ProbeTravelHistoryRepository historyRepository;

    @Mock
    private RemoteSeaProbeService remoteSeaProbeService;

    @Mock
    private ProbeMovementEngine movementEngine;

    @InjectMocks
    private ProbeTravelHistoryService historyService;

    @Test
    void shouldMoveProbeAndSaveHistory() {

        MovementRequest request = new MovementRequest();
        request.setProbeId(1L);
        request.setAction("FORWARD");

        RemoteSeaProbe probe = new RemoteSeaProbe();
        probe.setId(1L);
        probe.setInitialLatitude(5L);
        probe.setInitialLongitude(5L);
        probe.setDirectionFacing(FacingDirection.NORTH);

        MovementResult result = MovementResult.builder()
                .coordinates(new Coordinates(6, 5))
                .facing(FacingDirection.NORTH)
                .build();

        when(remoteSeaProbeService.getProbeEntity(1L)).thenReturn(probe);
        when(movementEngine.execute(eq(probe), eq(Command.FORWARD))).thenReturn(result);
        when(historyRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        TravelHistoryResponse response = historyService.moveProbe(request);

        assertNotNull(response);
        assertEquals("FORWARD", response.getAction());
        assertEquals(6, response.getLatitude());
        assertEquals(5, response.getLongitude());

        verify(historyRepository).save(any());
    }

    @Test
    void shouldExecuteMovementFlowInCorrectOrder() {
        // arrange
        MovementRequest request = new MovementRequest();
        request.setProbeId(1L);
        request.setAction("FORWARD");

        RemoteSeaProbe probe = new RemoteSeaProbe();
        probe.setId(1L);
        probe.setInitialLatitude(10L);
        probe.setInitialLongitude(10L);
        probe.setDirectionFacing(FacingDirection.valueOf("NORTH"));

        MovementResult result = MovementResult.builder()
                .coordinates(new Coordinates(11, 10))
                .facing(FacingDirection.NORTH)
                .build();

        when(remoteSeaProbeService.getProbeEntity(1L)).thenReturn(probe);

        when(movementEngine.execute(eq(probe), any(Command.class))).thenReturn(result);

        when(historyRepository.save(any(ProbeTravelHistory.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // act
        historyService.moveProbe(request);

        // assert ORDER
        InOrder inOrder = inOrder(
                remoteSeaProbeService,
                movementEngine,
                historyRepository
        );

        inOrder.verify(remoteSeaProbeService).getProbeEntity(1L);

        inOrder.verify(movementEngine).execute(eq(probe), any(Command.class));

        inOrder.verify(historyRepository).save(any(ProbeTravelHistory.class));
    }

}


