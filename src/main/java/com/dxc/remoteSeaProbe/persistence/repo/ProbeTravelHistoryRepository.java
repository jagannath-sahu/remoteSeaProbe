package com.dxc.remoteSeaProbe.persistence.repo;

import com.dxc.remoteSeaProbe.persistence.entity.ProbeTravelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProbeTravelHistoryRepository extends JpaRepository<ProbeTravelHistory, Long> {

    List<ProbeTravelHistory> findByProbeId(Long probeId);

    Optional<ProbeTravelHistory> findFirstByProbeIdOrderByCreatedAtDesc(Long probeId);
}

