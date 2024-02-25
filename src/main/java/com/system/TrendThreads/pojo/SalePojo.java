package com.system.TrendThreads.pojo;

import com.system.TrendThreads.entity.Product;

import java.sql.Date;

public class SalePojo {
    private Integer id;
    private Product product;
    private Double discountPercent;
    private Date startDate;
    private Date endDate;
}
