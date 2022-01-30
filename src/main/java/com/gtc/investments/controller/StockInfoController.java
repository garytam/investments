package com.gtc.investments.controller;

import com.gtc.investments.aop.MethodMDC;
import com.gtc.investments.model.GetStockInfoResponse;
import com.gtc.investments.model.TrackingStock;
import com.gtc.investments.service.StockInfoService;
import com.gtc.investments.transformer.TransformTrackingStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/")
public class StockInfoController {

    private static final Logger logger = LoggerFactory.getLogger(StockInfoController.class);

    @Autowired
    StockInfoService service;

    @Autowired
    TransformTrackingStock transformer;

    @RequestMapping("/stock/quote/{name}")
    @MethodMDC
    public ResponseEntity<GetStockInfoResponse> getStockInfo(
            @PathVariable("name") String stockList,
            @RequestHeader(value="transactionId") String transactionId) throws IOException
    {
        MDC.put("transactionId", transactionId);
        logger.info("getStockInfo starts --> [" + stockList + "]");
        List<String> stockSymbols = Arrays.asList(stockList.split(", [ ]*"));
        Map<String, Stock> stocks = service.getStockQuotes(stockSymbols);
        List<TrackingStock> trackingStocks = transformer.transformStocks(stocks.values().stream().collect(Collectors.toList()));

        GetStockInfoResponse response = new GetStockInfoResponse();
        response.getStockList().addAll(trackingStocks);
        return new ResponseEntity<>((GetStockInfoResponse)  response, HttpStatus.OK);

    }
}
