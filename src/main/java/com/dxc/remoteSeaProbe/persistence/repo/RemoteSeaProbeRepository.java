package com.dxc.remoteSeaProbe.persistence.repo;

import com.dxc.remoteSeaProbe.persistence.entity.RemoteSeaProbe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteSeaProbeRepository extends JpaRepository<RemoteSeaProbe, Long> {
}

