package com.ximouzhao.Result.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销项发票详情
 * @author JBS 2019-09-04
 * @Table(name = "sales_invoice_detail")
 */
public class SalesInvoiceDetail implements Serializable {
    /**
     * ID
	 * @Column(name = "id")
	 * @Id
     */
    private Integer id;

    /**
     * 订单编号
	 * @Column(name = "order_no")
     */
    private String orderNo;

    /**
     * 发票代码
	 * @Column(name = "invoice_code")
     */
    private String invoiceCode;

    /**
     * 发票号码
	 * @Column(name = "invoice_num")
     */
    private String invoiceNum;

    /**
     * 发票类型
	 * @Column(name = "invoice_type")
     */
    private Byte invoiceType;

    /**
     * 开票日期
	 * @Column(name = "invoice_date")
     */
    private Date invoiceDate;

    /**
     * 原始订单编号
	 * @Column(name = "original_order_code")
     */
    private String originalOrderCode;

    /**
     * 公司
	 * @Column(name = "corp")
     */
    private String corp;

    /**
     * 红冲状态
	 * @Column(name = "reded_status")
     */
    private Integer rededStatus;

    /**
     * 开票金额
	 * @Column(name = "invoice_mny")
     */
    private BigDecimal invoiceMny;

    /**
     * 返回操作码
	 * @Column(name = "retCode")
     */
    private String retcode;

    /**
     * 返回信息
	 * @Column(name = "retMsg")
     */
    private String retmsg;

    /**
     * 修改时间
	 * @Column(name = "update_time")
     */
    private Date updateTime;

    /**
     * 创建时间
	 * @Column(name = "create_time")
     */
    private Date createTime;

    /**
     * 电子发票系统生成的流水号
	 * @Column(name = "eserialno")
     */
    private String eserialno;

    /**
     * 购货方名称
	 * @Column(name = "nsrmc")
     */
    private String nsrmc;

    /**
     * 发票链接
	 * @Column(name = "invoice_link")
     */
    private String invoiceLink;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Byte getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Byte invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getOriginalOrderCode() {
        return originalOrderCode;
    }

    public void setOriginalOrderCode(String originalOrderCode) {
        this.originalOrderCode = originalOrderCode;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public Integer getRededStatus() {
        return rededStatus;
    }

    public void setRededStatus(Integer rededStatus) {
        this.rededStatus = rededStatus;
    }

    public BigDecimal getInvoiceMny() {
        return invoiceMny;
    }

    public void setInvoiceMny(BigDecimal invoiceMny) {
        this.invoiceMny = invoiceMny;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEserialno() {
        return eserialno;
    }

    public void setEserialno(String eserialno) {
        this.eserialno = eserialno;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getInvoiceLink() {
        return invoiceLink;
    }

    public void setInvoiceLink(String invoiceLink) {
        this.invoiceLink = invoiceLink;
    }
}