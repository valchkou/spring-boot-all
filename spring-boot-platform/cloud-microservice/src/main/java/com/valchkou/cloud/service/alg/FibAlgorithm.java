package com.valchkou.cloud.service.alg;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evalchkou on 9/22/2017.
 */
public abstract class FibAlgorithm {
    private static Map<String, FibAlgorithm> algs = new HashMap<>();

    public static FibAlgorithm get(String name) {
        return algs.get(name);
    }

    public static void register(FibAlgorithm a) {
        algs.put(a.getName(), a);
    }

    @PostConstruct
    private void init(){
        FibAlgorithm.register(this);
    }

    public abstract long calc(long i);
    public abstract String getName();
}
