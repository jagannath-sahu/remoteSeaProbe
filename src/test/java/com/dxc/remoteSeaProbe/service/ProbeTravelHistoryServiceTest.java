package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.MovementRequest;
import com.dxc.remoteSeaProbe.dto.ProbeResponse;
import com.dxc.remoteSeaProbe.dto.TravelHistoryResponse;
import com.dxc.remoteSeaProbe.persistence.entity.ProbeTravelHistory;
import com.dxc.remoteSeaProbe.persistence.repo.ProbeTravelHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProbeTravelHistoryServiceTest {

    @Mock
    private ProbeTravelHistoryRepository historyRepository;

    @Mock
    private RemoteSeaProbeService remoteSeaProbeService;

    @InjectMocks
    private ProbeTravelHistoryService historyService;

    @Test
    void shouldMoveProbeAndSaveHistory() {
        // given
        MovementRequest request = new MovementRequest();
        request.setProbeId(1L);
        request.setAction("FORWARD");

        ProbeResponse probe = new ProbeResponse(
                1L,
                "Probe-1",
                (long) 12.5,
                (long) 77.6,
                "NORTH",
                LocalDateTime.now()
        );

        when(remoteSeaProbeService.getProbe(1L)).thenReturn(probe);
        when(historyRepository.save(any(ProbeTravelHistory.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        TravelHistoryResponse response = historyService.moveProbe(request);

        // then
        assertNotNull(response);
        assertEquals(1L, response.getProbeId());
        assertEquals("FORWARD", response.getAction());

        verify(remoteSeaProbeService).getProbe(1L);
        verify(historyRepository).save(any());
    }
}

