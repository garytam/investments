package com.gtc.investments.transformer;

import com.gtc.investments.model.TrackingStock;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransformTrackingStock {

    public TrackingStock transformStock(Stock stock){
        TrackingStock trackingStock  = new TrackingStock(stock.getSymbol());
        trackingStock.setName(stock.getName());
        trackingStock.setCurrency(stock.getCurrency());
        trackingStock.setStockExchange(stock.getStockExchange());
        trackingStock.setQuote(stock.getQuote());
        trackingStock.setStats(stock.getStats());
        trackingStock.setDividend(stock.getDividend());

        return trackingStock;
    }

    public List<TrackingStock> transformStocks(List<Stock> stocks){
        List<TrackingStock> trackingStocks = new ArrayList<>();
        stocks.forEach( stock -> trackingStocks.add(this.transformStock(stock)));

        return trackingStocks;
    }
}
