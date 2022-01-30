package com.gtc.investments.delegate;

import com.gtc.investments.adapter.YahooFinanceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class StockInfoDelegate {

    @Autowired
    YahooFinanceAdapter adapter;

    public Map<String, Stock> getStockQuotes(List<String> stockSymbols) throws IOException {
        Map<String, Stock> stocks = adapter.getStockQuote(stockSymbols);
        return stocks;
    }
}
