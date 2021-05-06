package com.crypto.cryptoassignment;

import com.crypto.cryptoassignment.asserts.DataAssertion;
import com.crypto.cryptoassignment.businesslayer.CandlestickOperations;
import com.crypto.cryptoassignment.businesslayer.TradeOperations;
import com.crypto.cryptoassignment.businesslayer.TradePriceComparator;
import com.crypto.cryptoassignment.pojo.CandlestickData;
import com.crypto.cryptoassignment.pojo.TradeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
class CryptoAssignmentTests {

    @Autowired
    CandlestickOperations candlestickOperations;

    @Autowired
    TradeOperations tradeOperations;

    @Test
    @DisplayName("Verify Consistency for Candlestick Data for USDC_USDT " +
            "having time frame as 1 minute")
    void verifyConsistency_1m() {
        String instrumentName = "USDC_USDT";
        List<CandlestickData> candleStickDataList =
                candlestickOperations.getCandlestickDataAsList(instrumentName, "1m");
        Assert.notEmpty(candleStickDataList, "There is no Candlestick data " +
                "for the given instrument name and time frame");
        List<TradeData> tradeDataList =
                tradeOperations.getDataFromApiAsList(instrumentName);
        Assert.notEmpty(tradeDataList, "There is no trade data for the given " +
                "instrument name");
        Map<CandlestickData, List<TradeData>> tradeDataListForCandleStick =
                tradeOperations.getDataBasedOnCandleStickTimeframe(candleStickDataList, 1, ChronoUnit.MINUTES);
        DataAssertion.verify(tradeDataListForCandleStick);
    }

    @Test
    @DisplayName("Verify Consistency for Candlestick Data for BTC_USDT " +
            "having time frame as 5 minutes")
    void verifyConsistency_5m() {
        String instrumentName = "BTC_USDT";
        List<CandlestickData> candleStickDataList =
                candlestickOperations.getCandlestickDataAsList(instrumentName, "5m");
        Assert.notEmpty(candleStickDataList, "There is no Candlestick data " +
                "for the given instrument name and time frame");
        List<TradeData> tradeDataList =
                tradeOperations.getDataFromApiAsList(instrumentName);
        Assert.notEmpty(tradeDataList, "There is no trade data for the given " +
                "instrument name");
        Map<CandlestickData, List<TradeData>> tradeDataListForCandleStick =
                tradeOperations.getDataBasedOnCandleStickTimeframe(candleStickDataList, 5, ChronoUnit.MINUTES);
        DataAssertion.verify(tradeDataListForCandleStick);
    }
}
