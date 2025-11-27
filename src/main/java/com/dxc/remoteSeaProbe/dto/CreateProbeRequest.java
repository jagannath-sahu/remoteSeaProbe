package com.dxc.remoteSeaProbe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProbeRequest {
    private String name;
    private Long initialLatitude;
    private Long initialLongitude;
    private String directionFacing;
}

