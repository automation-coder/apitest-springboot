package com.crypto.cryptoassignment.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandlestickData {
    private long t;
    private double o;
    private double c;
    private double h;
    private double l;
    private double v;
}
