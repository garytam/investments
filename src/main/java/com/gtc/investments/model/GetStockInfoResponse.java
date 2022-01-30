package com.gtc.investments.model;

import java.util.ArrayList;
import java.util.List;

public class GetStockInfoResponse {

    List<TrackingStock> stockList;

    public List<TrackingStock> getStockList() {
        if (stockList == null){
            stockList = new ArrayList<>();
        }
        return stockList;
    }

    public void setStockList(List<TrackingStock> stockList) {
        this.stockList = stockList;
    }
}
