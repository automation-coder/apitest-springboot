package com.crypto.cryptoassignment.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeData {
    private long dataTime;
    private double p;
    private double q;
    private long t;
    private long d;
    private String s;
    private String i;
}
