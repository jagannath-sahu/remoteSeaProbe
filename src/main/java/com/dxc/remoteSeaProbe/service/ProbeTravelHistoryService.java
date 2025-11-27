package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.MovementDirection;
import com.dxc.remoteSeaProbe.dto.MovementRequest;
import com.dxc.remoteSeaProbe.dto.TravelHistoryResponse;
import com.dxc.remoteSeaProbe.persistence.entity.ProbeTravelHistory;
import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import com.dxc.remoteSeaProbe.persistence.repo.ProbeTravelHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProbeTravelHistoryService {

    private final ProbeTravelHistoryRepository historyRepository;

    private final RemoteSeaProbeService remoteSeaProbeService;

    public ProbeTravelHistoryService(ProbeTravelHistoryRepository historyRepository, RemoteSeaProbeService remoteSeaProbeService) {
        this.historyRepository = historyRepository;
        this.remoteSeaProbeService = remoteSeaProbeService;
    }

    @Transactional
    public TravelHistoryResponse moveProbe(MovementRequest request) {

        var probe = remoteSeaProbeService.getProbe(request.getProbeId());

        MovementDirection direction = MovementDirection.fromString(request.getAction());

        var newPosition = direction.move(probe);

        ProbeTravelHistory history = new ProbeTravelHistory();
        history.setAction(direction);
        history.setLatitude(newPosition.latitude());
        history.setLongitude(newPosition.longitude());
        RemoteSeaProbe remoteSeaProbe = new RemoteSeaProbe();
        remoteSeaProbe.setId(probe.getId());
        remoteSeaProbe.setName(probe.getName());
        remoteSeaProbe.setCreatedAt(probe.getCreatedAt());
        remoteSeaProbe.setInitialLongitude(probe.getLongitude());
        remoteSeaProbe.setInitialLatitude(probe.getLatitude());
        remoteSeaProbe.setDirectionFacing(probe.getDirectionFacing());
        history.setProbe(remoteSeaProbe);
        history.setCreatedAt(LocalDateTime.now());
        ProbeTravelHistory saved = historyRepository.save(history);

        return toResponse(saved);
    }

    public List<TravelHistoryResponse> getFullHistory(Long probeId) {
        return historyRepository.findByProbeId(probeId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TravelHistoryResponse getCurrentPosition(Long probeId) {
        return historyRepository.findFirstByProbeIdOrderByCreatedAtDesc(probeId)
                .map(this::toResponse)
                .orElse(null);
    }

    private TravelHistoryResponse toResponse(ProbeTravelHistory e) {
        return new TravelHistoryResponse(
                e.getId(),
                e.getProbe().getId(),
                e.getAction().name(),
                e.getLatitude(),
                e.getLongitude(),
                e.getCreatedAt()
        );
    }
}

