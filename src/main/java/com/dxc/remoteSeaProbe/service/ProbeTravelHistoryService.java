package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.*;
import com.dxc.remoteSeaProbe.mapper.RemoteSeaProbeMapper;
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

    private final RemoteSeaProbeMapper mapper;

    public ProbeTravelHistoryService(ProbeTravelHistoryRepository historyRepository, RemoteSeaProbeService remoteSeaProbeService, RemoteSeaProbeMapper mapper) {
        this.historyRepository = historyRepository;
        this.remoteSeaProbeService = remoteSeaProbeService;
        this.mapper = mapper;
    }

    @Transactional
    public TravelHistoryResponse moveProbe(MovementRequest request) {

        // 1. Load managed entity
        RemoteSeaProbe probe = remoteSeaProbeService.getProbeEntity(request.getProbeId());

        // 2. Resolve direction
        MovementDirection direction =
                MovementDirection.fromString(request.getAction());

        // 3. Calculate new position
        var newPosition = direction.move(mapper.toResponse(probe));

        // 4. Create history entry
        ProbeTravelHistory history =
                createHistory(probe, direction, newPosition);

        // 5. Save & return
        return toResponse(historyRepository.save(history));
    }

    private ProbeTravelHistory createHistory(RemoteSeaProbe probe,
                                             MovementDirection direction,
                                             Coordinates newPosition) {

        ProbeTravelHistory history = new ProbeTravelHistory();
        history.setProbe(probe); // 🔥 managed entity
        history.setAction(direction);
        history.setLatitude(newPosition.latitude());
        history.setLongitude(newPosition.longitude());
        history.setCreatedAt(LocalDateTime.now());
        return history;
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

    private TravelHistoryResponse toResponse(ProbeTravelHistory probeTravelHistory) {
        return new TravelHistoryResponse(
                probeTravelHistory.getId(),
                probeTravelHistory.getProbe().getId(),
                probeTravelHistory.getAction().name(),
                probeTravelHistory.getLatitude(),
                probeTravelHistory.getLongitude(),
                probeTravelHistory.getCreatedAt()
        );
    }
}

