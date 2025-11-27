package com.dxc.remoteSeaProbe.service;

import org.springframework.stereotype.Service;

import com.dxc.remoteSeaProbe.dto.Coordinates;
import com.dxc.remoteSeaProbe.dto.MovementDirection;

@Service
public class MovementService {

    public Coordinates calculateNextPosition(Coordinates current,
                                             MovementDirection action) {

        double newLat = current.latitude() + action.getLatDelta();
        double newLon = current.longitude() + action.getLonDelta();

        return new Coordinates(newLat, newLon);
    }
}

