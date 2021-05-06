package com.crypto.cryptoassignment.businesslayer;

import com.crypto.cryptoassignment.pojo.CandlestickData;
import com.crypto.cryptoassignment.pojo.TradeData;
import com.crypto.cryptoassignment.util.CommonActions;
import com.crypto.cryptoassignment.util.RestActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TradeOperations {

    private List<TradeData> tradeDataList;
    private final String ENDPOINT = "/get-trades";

    @Autowired
    RestActions restActions;

    public List<TradeData> getDataFromApiAsList(String instrumentName) {
        RestActions.initialize();
        List<TradeData> tradeDataList = new ArrayList<>();
        tradeDataList.addAll((Collection<? extends TradeData>) restActions.getDataAsList(ENDPOINT,
                TradeData.class, instrumentName, ""));
        this.tradeDataList = tradeDataList;
        return tradeDataList;
    }

    public Map<CandlestickData, List<TradeData>> getDataBasedOnCandleStickTimeframe(List<CandlestickData> candlestickDataList,
                                                                                    int timeAsInteger,
                                                                                    ChronoUnit timeUnit) {
        //Getting time in milliseconds based on the value and timeunit
        // provided from the test
        long timeInMillis = CommonActions.getTimeInMillis(timeAsInteger,
                timeUnit);
        Map<CandlestickData, List<TradeData>> tradeDataListForCandleStick = new HashMap<>();
        for (CandlestickData candlestickData : candlestickDataList) {
            // As per the problem statement, we are going backward from the
            // candlestick endTime.
            // e.g. if the timeframe entered is 1 Minute and end time for a
            // candlestick is 2021-05-05 03:16:00 pm,
            // trade data will be filtered between 03:15:01 pm & 03:16:00 pm
            Predicate<TradeData> byTime = tradeData ->
                    tradeData.getT() <= candlestickData.getT() && tradeData.getT() > (candlestickData.getT() - timeInMillis);
            List<TradeData> filteredTradeDataByTimeFrame =
                    tradeDataList.stream().filter(byTime).collect(Collectors.toList());
            if (filteredTradeDataByTimeFrame.size() > 0) {
                // we are using a map to keep a track of the trades done
                // between the timeframe for each candlestick data
                tradeDataListForCandleStick.put(candlestickData,
                        filteredTradeDataByTimeFrame);
            }
        }
        return tradeDataListForCandleStick;
    }
}
