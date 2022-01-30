package com.gtc.investments.service;

import com.gtc.investments.delegate.StockInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class StockInfoService {

    @Autowired
    StockInfoDelegate stockInfoDelegate;

    public Map<String, Stock> getStockQuotes(List<String> stockSymbols) throws IOException {
        Map<String, Stock> stocks = stockInfoDelegate.getStockQuotes(stockSymbols);
        return stocks;
    }
}
