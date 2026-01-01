package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.ProbeResponse;
import com.dxc.remoteSeaProbe.mapper.RemoteSeaProbeMapper;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.dto.CreateProbeRequest;
import com.dxc.remoteSeaProbe.persistence.repo.RemoteSeaProbeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RemoteSeaProbeService {

    private final RemoteSeaProbeRepository probeRepository;

    private final RemoteSeaProbeMapper mapper;

    public RemoteSeaProbeService(RemoteSeaProbeRepository probeRepository, RemoteSeaProbeMapper mapper) {
        this.probeRepository = probeRepository;
        this.mapper = mapper;
    }

    public ProbeResponse createProbe(CreateProbeRequest request) {
        RemoteSeaProbe probe = mapper.toEntity(request);
        probe.setCreatedAt(LocalDateTime.now());
        RemoteSeaProbe saved = probeRepository.save(probe);
        return toResponse(saved);
    }

    public ProbeResponse getProbe(Long id) {
        RemoteSeaProbe probe = probeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Probe not found: " + id));

        return toResponse(probe);
    }

    public RemoteSeaProbe getProbeEntity(Long probeId) {
        return probeRepository.findById(probeId)
                .orElseThrow(() -> new EntityNotFoundException("Probe not found"));
    }

    private ProbeResponse toResponse(RemoteSeaProbe entity) {
        return mapper.toResponse(entity);
    }
}

