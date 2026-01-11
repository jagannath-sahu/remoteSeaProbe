package com.dxc.remoteSeaProbe.kafka.dlq;

import com.dxc.remoteSeaProbe.kafka.SecurityMatchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dlq")
public class DlqReplayController {

    private final DlqReplayService replayService;

    @PostMapping("/replay")
    public String replay(@RequestBody SecurityMatchEvent event) {
        replayService.replay(event);
        return "Replayed successfully";
    }
}

