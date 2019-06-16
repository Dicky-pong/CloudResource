package com.amazingfour.crms.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockMonitor implements Serializable {

    private Long id;

    private String stockCode;

    private BigDecimal lowPrice;

    private BigDecimal highPrice;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StockMonitor{" +
                "id=" + id +
                ", stockCode='" + stockCode + '\'' +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", email='" + email + '\'' +
                '}';
    }
}
