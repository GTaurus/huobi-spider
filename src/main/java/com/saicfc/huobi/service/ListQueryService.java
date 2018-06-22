package com.saicfc.huobi.service;

import com.saicfc.huobi.consts.Constants;
import com.saicfc.huobi.models.HuobiQueryRespModel;
import com.saicfc.huobi.models.HuobiQueryRespModel.PaymentInfo;
import com.saicfc.huobi.models.ListQueryRespModel;
import com.saicfc.huobi.models.ListQueryRespModel.Result;
import com.saicfc.huobi.support.HuobiUrlWrapper;
import com.saicfc.huobi.utils.HuobiUtils;
import com.saicfc.huobi.worker.GetOneWorkingThread;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ListQueryService {

  @Autowired
  private HuobiUrlWrapper urlWrapper;

  public ListQueryRespModel query(String coinType, String tradeDesc) {
    String coinId = getCoinId(coinType);
    List<PaymentInfo> list = getList(coinId, getTradeType(tradeDesc));

    ListQueryRespModel respModel = new ListQueryRespModel();
    respModel.setResult(getResult(list));
    return respModel;
  }

  private String getTradeType(String tradeDesc) {
    switch (tradeDesc) {
      case "buy":
        return Constants.TRADE_TYPE_BUY;
      case "sale":
        return Constants.TRADE_TYPE_SALE;
      default:
        return "0";
    }
  }

  private Map<String, Result> getResult(List<PaymentInfo> list) {
    Map<String, Result> map = new HashMap<>();
    for (PaymentInfo e : list) {
      String k = e.getPrice().toString();
      Result r = map.getOrDefault(k, new Result());
      if (!map.containsKey(k)) {
        map.put(k, r);
      }
      r.setTimes(r.getTimes() + 1);
      r.setTradeCount(e.getTradeCount().add(r.getTradeCount() == null ? BigDecimal.ZERO : r.getTradeCount()));
    }
    return map;
  }


  private List<PaymentInfo> getList(String coinId, String tradeType) {
    int currPage = 1;
    HuobiQueryRespModel oneTime;
    List<PaymentInfo> list = new ArrayList<>();
    String url = urlWrapper.getQueryUrl(currPage, coinId, tradeType);
    oneTime = HuobiUtils.getOneTime(url);
    list.addAll(oneTime.getData());
    if (oneTime.getTotalPage() > currPage) {
      CountDownLatch cdl = new CountDownLatch(oneTime.getTotalPage() - currPage);
      while (oneTime.getTotalPage() > currPage++) {

        new GetOneWorkingThread(cdl, list, urlWrapper.getQueryUrl(currPage, coinId, tradeType)).start();
      }
      try {
        cdl.await();
      } catch (Exception e) {
        log.error("", e);
      }

    }
    return list;
  }

  private String getCoinId(String coinType) {
    switch (coinType) {
      case "BTH":
        return Constants.COIN_BTC;
      case "ETH":
        return Constants.COIN_ETH;
      case "USDT":
        return Constants.COIN_USDT;
      default:
        return "0";
    }
  }
}
