package com.dxc.remoteSeaProbe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProbeResponse {

    private Long id;
    private String name;
    private Long latitude;
    private Long longitude;
    private String directionFacing;
    private LocalDateTime createdAt;

    public ProbeResponse(
            Long id, String name, Long latitude,
            Long longitude, String directionFacing,
            LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.directionFacing = directionFacing;
        this.createdAt = createdAt;
    }

    // getters only (no setters)
}

