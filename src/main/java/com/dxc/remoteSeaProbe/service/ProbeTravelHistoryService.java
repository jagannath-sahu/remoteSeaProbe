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

    //private final RemoteSeaProbeMapper mapper;

    private final ProbeMovementEngine movementEngine;

    public ProbeTravelHistoryService(ProbeTravelHistoryRepository historyRepository, RemoteSeaProbeService remoteSeaProbeService, ProbeMovementEngine movementEngine) {
        this.historyRepository = historyRepository;
        this.remoteSeaProbeService = remoteSeaProbeService;
        this.movementEngine = movementEngine;
    }

    @Transactional
    public TravelHistoryResponse moveProbe(MovementRequest request) {

        RemoteSeaProbe probe =
                remoteSeaProbeService.getProbeEntity(request.getProbeId());

        Command command = Command.from(request.getAction());

        MovementResult result =
                movementEngine.execute(probe, command);

        // update probe state
        probe.setInitialLatitude((long) result.getCoordinates().latitude());
        probe.setInitialLongitude((long) result.getCoordinates().longitude());
        probe.setDirectionFacing(result.getFacing());

        ProbeTravelHistory history = new ProbeTravelHistory();
        history.setProbe(probe);
        history.setAction(MovementDirection.valueOf(command.name()));
        history.setLatitude(result.getCoordinates().latitude());
        history.setLongitude(result.getCoordinates().longitude());

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

