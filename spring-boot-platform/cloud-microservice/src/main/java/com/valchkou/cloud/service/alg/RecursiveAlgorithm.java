package com.valchkou.cloud.service.alg;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by evalchkou on 9/22/2017.
 */
@Component
public class RecursiveAlgorithm extends FibAlgorithm {

    @Override
    public long calc(long n) {
        if (n==0) return 0;
        if (n<3) return 1;
        return calc(n-1)+ calc(n-2);
    }

    @Override
    public String getName() {
        return "RECURSIVE";
    }
}
