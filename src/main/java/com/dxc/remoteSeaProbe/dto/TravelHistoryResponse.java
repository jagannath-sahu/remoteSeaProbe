package com.dxc.remoteSeaProbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TravelHistoryResponse {

    private final Long id;
    private final Long probeId;
    private final String action;
    private final Double latitude;
    private final Double longitude;
    private final LocalDateTime createdAt;

    public TravelHistoryResponse(Long id, Long probeId, String action,
                                 Double latitude, Double longitude,
                                 LocalDateTime createdAt) {

        this.id = id;
        this.probeId = probeId;
        this.action = action;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }
}

