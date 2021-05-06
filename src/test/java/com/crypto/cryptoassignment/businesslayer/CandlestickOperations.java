package com.crypto.cryptoassignment.businesslayer;

import com.crypto.cryptoassignment.pojo.CandlestickData;
import com.crypto.cryptoassignment.util.RestActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandlestickOperations {

    private final String ENDPOINT = "/get-candlestick";

    @Autowired
    RestActions restActions;

    public List<CandlestickData> getCandlestickDataAsList(String instrumentName, String timeframe) {
        RestActions.initialize();
        List<?> candlestickData = restActions.getDataAsList(ENDPOINT,
                CandlestickData.class, instrumentName, timeframe);
        return (List<CandlestickData>) candlestickData;
    }
}
