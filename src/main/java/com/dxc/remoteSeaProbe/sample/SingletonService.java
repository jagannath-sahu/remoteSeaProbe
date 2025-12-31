package com.dxc.remoteSeaProbe.sample;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class SingletonService {

    /*
    //use this to test like when we inject prototype into singleton we didnt get different instance,
    //so the correct way is to use ObjectProvider to get different instance
    private final PrototypeService prototypeService;

    public SingletonService(PrototypeService prototypeService) {
        this.prototypeService = prototypeService;
    }*/

    //use ObjectProvider when inject prototype bean into singleton
    private final ObjectProvider<PrototypeService> provider;

    public SingletonService(ObjectProvider<PrototypeService> provider) {
        this.provider = provider;
    }

    public int printHash() {
        PrototypeService prototypeService = provider.getObject();
        return prototypeService.hashCode();
    }
}

