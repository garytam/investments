package com.gtc.investments.adapter;

import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class YahooFinanceAdapter {
    public Map<String, Stock> getStockQuote(List<String> stockSymbols) throws IOException {

        String[] symboleArr = stockSymbols.toArray(new String[stockSymbols.size()]);

        Map<String, Stock> stocks = YahooFinance.get(symboleArr);

        return stocks;

    }
}
