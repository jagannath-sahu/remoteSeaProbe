package com.dxc.remoteSeaProbe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovementRequest {
    private Long probeId;
    private String action; // left, right, forward, backward, stay

}

