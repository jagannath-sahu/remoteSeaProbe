package com.dxc.remoteSeaProbe.controller;

import com.dxc.remoteSeaProbe.dto.*;
import com.dxc.remoteSeaProbe.service.ProbeTravelHistoryService;
import com.dxc.remoteSeaProbe.service.RemoteSeaProbeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/probes")
public class ProbeController {

    private final RemoteSeaProbeService probeService;
    private final ProbeTravelHistoryService historyService;

    public ProbeController(RemoteSeaProbeService probeService,
                           ProbeTravelHistoryService historyService) {
        this.probeService = probeService;
        this.historyService = historyService;
    }

    @PostMapping
    public ResponseEntity<ProbeResponse> createProbe(@RequestBody CreateProbeRequest request) {
        return ResponseEntity.ok(probeService.createProbe(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProbeResponse> getProbe(@PathVariable Long id) {
        return ResponseEntity.ok(probeService.getProbe(id));
    }

    @PostMapping("/move")
    public ResponseEntity<TravelHistoryResponse> move(@RequestBody MovementRequest request) {
        return ResponseEntity.ok(historyService.moveProbe(request));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<TravelHistoryResponse>> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.getFullHistory(id));
    }

    @GetMapping("/{id}/current")
    public ResponseEntity<TravelHistoryResponse> current(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.getCurrentPosition(id));
    }
}

