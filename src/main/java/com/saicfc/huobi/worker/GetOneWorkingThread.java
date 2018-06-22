package com.saicfc.huobi.worker;

import com.saicfc.huobi.models.HuobiQueryRespModel.PaymentInfo;
import com.saicfc.huobi.utils.HuobiUtils;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GetOneWorkingThread extends Thread {

  private CountDownLatch cdl;
  private List<PaymentInfo> list;
  private String url;

  public GetOneWorkingThread(CountDownLatch cdl, List<PaymentInfo> list, String url) {
    this.cdl = cdl;
    this.list = list;
    this.url = url;
  }

  @Override
  public void run() {
    list.addAll(HuobiUtils.getOneTime(url).getData());
    cdl.countDown();
  }


}
