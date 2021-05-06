package com.crypto.cryptoassignment.businesslayer;

import com.crypto.cryptoassignment.pojo.TradeData;

import java.util.Comparator;

public class TradeTimeComparator implements Comparator<TradeData> {
    @Override
    public int compare(TradeData t1, TradeData t2) {
        if (t1.getT() > t2.getT()) {
            return 1;
        }
        if (t1.getT() < t2.getT()) {
            return -1;
        }
        return 0;
    }
}
