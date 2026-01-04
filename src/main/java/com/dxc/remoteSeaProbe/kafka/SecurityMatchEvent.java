package com.dxc.remoteSeaProbe.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityMatchEvent {

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("security_id")
    private String securityId;

    @JsonProperty("source_system")
    private String sourceSystem;

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("payload_hash")
    private String payloadHash; //payload_hash → duplicate detection

    @JsonProperty("created_at")
    private Instant createdAt;
}

