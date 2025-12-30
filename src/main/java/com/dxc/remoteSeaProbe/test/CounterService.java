package com.dxc.remoteSeaProbe.test;

import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private int counter = 0; // 🚨 shared mutable state

    public int getCounter() {
        return counter;
    }

    public int increment() {
        counter++;
        return counter;
    }
}

