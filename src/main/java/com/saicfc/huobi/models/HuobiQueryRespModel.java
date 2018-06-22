package com.saicfc.huobi.models;

import java.math.BigDecimal;
import java.util.List;

public class HuobiQueryRespModel {

  private String code;
  private String message;
  private int totalCount;
  private int pageSize;
  private int totalPage;
  private int currPage;

  private List<PaymentInfo> data;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getCurrPage() {
    return currPage;
  }

  public void setCurrPage(int currPage) {
    this.currPage = currPage;
  }

  public List<PaymentInfo> getData() {
    return data;
  }

  public void setData(List<PaymentInfo> data) {
    this.data = data;
  }

  public static class PaymentInfo {
    private BigDecimal price;
    private BigDecimal tradeCount;

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
    }

    public BigDecimal getTradeCount() {
      return tradeCount;
    }

    public void setTradeCount(BigDecimal tradeCount) {
      this.tradeCount = tradeCount;
    }
  }
}
