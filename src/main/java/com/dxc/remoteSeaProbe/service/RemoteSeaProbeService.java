package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.ProbeResponse;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.dto.CreateProbeRequest;
import com.dxc.remoteSeaProbe.persistence.repo.RemoteSeaProbeRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RemoteSeaProbeService {

    private final RemoteSeaProbeRepository probeRepository;

    public RemoteSeaProbeService(RemoteSeaProbeRepository probeRepository) {
        this.probeRepository = probeRepository;
    }

    public ProbeResponse createProbe(CreateProbeRequest request) {
        RemoteSeaProbe probe = new RemoteSeaProbe();
        probe.setName(request.getName());
        probe.setInitialLatitude(request.getInitialLatitude());
        probe.setInitialLongitude(request.getInitialLongitude());
        probe.setDirectionFacing(request.getDirectionFacing());
        probe.setCreatedAt(LocalDateTime.now());

        RemoteSeaProbe saved = probeRepository.save(probe);

        return toResponse(saved);
    }

    public ProbeResponse getProbe(Long id) {
        RemoteSeaProbe probe = probeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Probe not found: " + id));

        return toResponse(probe);
    }

    private ProbeResponse toResponse(RemoteSeaProbe entity) {
        return new ProbeResponse(
                entity.getId(),
                entity.getName(),
                entity.getInitialLatitude(),
                entity.getInitialLongitude(),
                entity.getDirectionFacing(),
                entity.getCreatedAt()
        );
    }
}

