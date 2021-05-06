package com.crypto.cryptoassignment.asserts;

import com.crypto.cryptoassignment.businesslayer.TradePriceComparator;
import com.crypto.cryptoassignment.businesslayer.TradeTimeComparator;
import com.crypto.cryptoassignment.pojo.CandlestickData;
import com.crypto.cryptoassignment.pojo.TradeData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataAssertion {
    public static void verify(Map<CandlestickData, List<TradeData>> tradeDataListForCandleStick) {
        tradeDataListForCandleStick.forEach((candlestickData, tradeDataList) -> {
            if (tradeDataList.size() == 1) {
                log.info("Verifying Candlestick data for single trade");
                double tradePrice = tradeDataList.get(0).getP();
                Assertions.assertAll("Opening, Closing, High and Low Prices " +
                                "should be same as Trade price",
                        () -> Assertions.assertEquals(tradePrice,
                                candlestickData.getO(), "Opening price didn't " +
                                        "match with Trade Price"),
                        () -> Assertions.assertEquals(tradePrice,
                                candlestickData.getC(), "Closing price didn't " +
                                        "match with Trade Price"),
                        () -> Assertions.assertEquals(tradePrice,
                                candlestickData.getH(), "High price didn't " +
                                        "match with Trade Price"),
                        () -> Assertions.assertEquals(tradePrice,
                                candlestickData.getL(), "Low price didn't " +
                                        "match with Trade Price")
                );
            } else if (tradeDataList.size() >= 2) {
                log.info("Verifying Candlestick data for multiple trades");
                // This sort is based on the trade time in ascending order
                tradeDataList.sort(new TradeTimeComparator());
                double openingPrice = tradeDataList.get(0).getP();
                double closingPrice =
                        tradeDataList.get(tradeDataList.size() - 1).getP();
                // This sort is based on the Price in ascending order
                tradeDataList.sort(new TradePriceComparator());
                double lowPrice = tradeDataList.get(0).getP();
                double highPrice =
                        tradeDataList.get(tradeDataList.size() - 1).getP();
                Assertions.assertAll("Opening, Closing, High and Low Prices " +
                                "should match with the Trade prices",
                        () -> Assertions.assertEquals(openingPrice,
                                candlestickData.getO(), "Opening price should" +
                                        " be same as the 1st trade price"),
                        () -> Assertions.assertEquals(closingPrice,
                                candlestickData.getC(), "Closing price should" +
                                        " be same as the last trade price"),
                        () -> Assertions.assertEquals(highPrice,
                                candlestickData.getH(), "High price should be" +
                                        " same as the max trade price"),
                        () -> Assertions.assertEquals(lowPrice,
                                candlestickData.getL(), "Low price should be " +
                                        "same as the min trade price")
                );
            } else {
                log.info("No trade data found for given timeframe");
                Assertions.assertTrue(tradeDataList.size() == 0);
            }
        });
    }
}
