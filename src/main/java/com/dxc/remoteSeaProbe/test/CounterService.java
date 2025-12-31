package com.dxc.remoteSeaProbe.test;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CounterService {

    private int counter = 0; // 🚨 shared mutable state

    public int increment() {
        counter++;
        return counter;
    }
}

