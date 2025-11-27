package com.dxc.remoteSeaProbe.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "remote_sea_probe")
@Getter
@Setter
public class RemoteSeaProbe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "initial_latitude")
    private Long initialLatitude;

    @Column(name = "initial_longitude")
    private Long initialLongitude;

    @Column(name = "direction_facing")
    private String directionFacing;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

