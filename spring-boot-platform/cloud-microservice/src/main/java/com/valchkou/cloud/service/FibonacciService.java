package com.valchkou.cloud.service;


import com.valchkou.cloud.model.CalcMode;
import com.valchkou.cloud.model.FibonacciResult;
import com.valchkou.cloud.service.alg.FibAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    public FibonacciResult calcNthNumber(long nth, CalcMode mode) {
        long t = System.currentTimeMillis();
        long result = 0;
        FibAlgorithm alg = FibAlgorithm.get(mode.name());
        result = alg.calc(nth);
        return new FibonacciResult(result, System.currentTimeMillis() - t);
    }

}
