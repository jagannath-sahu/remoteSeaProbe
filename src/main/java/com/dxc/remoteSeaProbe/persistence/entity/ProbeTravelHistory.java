package com.dxc.remoteSeaProbe.persistence.entity;

import com.dxc.remoteSeaProbe.dto.MovementDirection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "probe_travel_history")
@Getter
@Setter
public class ProbeTravelHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probe_id", nullable = false)
    private RemoteSeaProbe probe;

    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    private MovementDirection action;

    private double latitude;
    private double longitude;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
