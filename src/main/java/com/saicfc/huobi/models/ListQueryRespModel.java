package com.saicfc.huobi.models;

import java.math.BigDecimal;
import java.util.Map;

public class ListQueryRespModel {

  private String status = "1";

  private Map<String, Result> result;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Map<String, Result> getResult() {
    return result;
  }

  public void setResult(Map<String, Result> result) {
    this.result = result;
  }

  public static class Result {

    private BigDecimal tradeCount;
    private int times;

    public BigDecimal getTradeCount() {
      return tradeCount;
    }

    public void setTradeCount(BigDecimal tradeCount) {
      this.tradeCount = tradeCount;
    }

    public int getTimes() {
      return times;
    }

    public void setTimes(int times) {
      this.times = times;
    }
  }
}