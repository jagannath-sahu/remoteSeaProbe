package com.dxc.remoteSeaProbe.dto;

public class MovementRequest {
    private Long probeId;
    private String action; // left, right, forward, backward, stay

    public Long getProbeId() { return probeId; }
    public void setProbeId(Long probeId) { this.probeId = probeId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}

