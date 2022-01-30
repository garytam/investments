package com.gtc.investments.model;

import lombok.Getter;
import lombok.Setter;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.util.Objects;

public class TrackingStock {

    @Getter
    private final String symbol;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String currency;

    @Getter @Setter
    private String stockExchange;

    @Getter @Setter
    private StockQuote quote;

    @Getter @Setter
    private StockStats stats;

    @Getter @Setter
    private StockDividend dividend;

    public TrackingStock(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackingStock that = (TrackingStock) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "TrackingStock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", stockExchange='" + stockExchange + '\'' +
                ", quote=" + quote +
                ", stats=" + stats +
                ", dividend=" + dividend +
                '}';
    }
}
