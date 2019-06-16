package com.amazingfour.crms.dao;

import com.amazingfour.crms.domain.StockMonitor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockMonitorDao")
public interface StockMonitorDao extends BaseDao<StockMonitor,Long>{
    public void insertStock(StockMonitor stockMonitor);

    public List<StockMonitor> findAll();
}
