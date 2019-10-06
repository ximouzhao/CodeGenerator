package com.ximouzhao.Result.dao;

import com.ximouzhao.Result.model.SalesInvoiceDetail;

public interface SalesInvoiceDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesInvoiceDetail record);

    int insertSelective(SalesInvoiceDetail record);

    SalesInvoiceDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesInvoiceDetail record);

    int updateByPrimaryKeyWithBLOBs(SalesInvoiceDetail record);

    int updateByPrimaryKey(SalesInvoiceDetail record);
}