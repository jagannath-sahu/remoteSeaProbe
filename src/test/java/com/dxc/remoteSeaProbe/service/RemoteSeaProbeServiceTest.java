package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.CreateProbeRequest;
import com.dxc.remoteSeaProbe.dto.ProbeResponse;
import com.dxc.remoteSeaProbe.mapper.RemoteSeaProbeMapper;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.persistence.repo.RemoteSeaProbeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoteSeaProbeServiceTest {

    @Mock
    private RemoteSeaProbeRepository probeRepository;

    @Mock
    private RemoteSeaProbeMapper mapper;

    @InjectMocks
    private RemoteSeaProbeService probeService;

    @Test
    void shouldCreateProbeSuccessfully() {
        // given
        CreateProbeRequest request = new CreateProbeRequest();
        request.setName("Probe-1");
        request.setDirectionFacing("NORTH");
        request.setInitialLatitude((long) 12.5);
        request.setInitialLongitude((long) 77.6);

        RemoteSeaProbe savedProbe = new RemoteSeaProbe();
        savedProbe.setId(1L);
        savedProbe.setName("Probe-1");
        savedProbe.setInitialLatitude((long) 12.5);
        savedProbe.setInitialLongitude((long) 77.6);
        savedProbe.setDirectionFacing("NORTH");
        savedProbe.setCreatedAt(LocalDateTime.now());

        when(probeRepository.save(any(RemoteSeaProbe.class)))
                .thenReturn(savedProbe);

        when(mapper.toEntity(any(CreateProbeRequest.class)))
                .thenReturn(savedProbe);

        ProbeResponse probeResponse = new ProbeResponse(1L,
                "Probe-1",
                (long) 12.5,
                (long) 77.6,
                "NORTH",
                LocalDateTime.now());

        when(mapper.toResponse(any(RemoteSeaProbe.class)))
                .thenReturn(probeResponse);

        // when
        ProbeResponse response = probeService.createProbe(request);

        // then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Probe-1", response.getName());
        verify(probeRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenProbeNotFound() {
        // given
        when(probeRepository.findById(99L))
                .thenReturn(Optional.empty());

        // then
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> probeService.getProbe(99L)
        );

        assertEquals("Probe not found: 99", ex.getMessage());
    }
}

