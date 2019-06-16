package com.amazingfour.crms.service.impl;

import com.amazingfour.crms.dao.StockMonitorDao;
import com.amazingfour.crms.domain.StockMonitor;
import com.amazingfour.crms.service.StockMonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stockMonitorService")
public class StockMonitorServiceImpl extends AbstractService<StockMonitor,Long> implements StockMonitorService {

    @Resource
    private StockMonitorDao stockMonitorDao;

    @Override
    public void insertStock(StockMonitor stockMonitor) {
        stockMonitorDao.insertStock(stockMonitor);

    }

    @Override
    public List<StockMonitor> findAll() {
        return stockMonitorDao.findAll();
    }
}
