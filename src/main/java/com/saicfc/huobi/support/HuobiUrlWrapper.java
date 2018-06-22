package com.saicfc.huobi.support;

import com.saicfc.huobi.consts.Constants;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HuobiUrlWrapper {

  private final static String QUERY_STRING = "country=%s&currency=%s&payMethod=%s&currPage=%s&coinId=%s&tradeType=%s&merchant=1&online=1";

  @Value("${huobi.host}")
  private String host;
  @Value("${huobi.path}")
  private String path;
  private String URL;

  @PostConstruct
  public void init() {
    URL = host + path;
  }


  public String getQueryUrl(int currPage, String coinId, String tradeType) {
    return getQueryUrl(Constants.COUNTRY_CHINA, Constants.CURRENCY_CNY, Constants.PAY_METHOD_ALL, currPage, coinId,
        tradeType);
  }

  public String getQueryUrl(String country, String currency, String payMethod, int currPage, String coinId,
      String tradeType) {
    return URL + '?' + String.format(QUERY_STRING, country, currency, payMethod, currPage, coinId, tradeType);
  }

}
