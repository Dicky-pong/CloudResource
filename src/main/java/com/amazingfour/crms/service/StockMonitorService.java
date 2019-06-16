package com.amazingfour.crms.service;

import com.amazingfour.crms.domain.StockMonitor;

import java.util.List;

public interface StockMonitorService extends BaseService<StockMonitor,Long>{

    public void insertStock(StockMonitor stockMonitor);

    public List<StockMonitor> findAll();
}
