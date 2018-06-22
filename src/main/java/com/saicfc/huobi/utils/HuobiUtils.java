package com.saicfc.huobi.utils;

import com.alibaba.fastjson.JSON;
import com.saicfc.huobi.models.HuobiQueryRespModel;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

@Log4j2
public class HuobiUtils {

  private final static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
      .connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS)).build();

  public static HuobiQueryRespModel getOneTime(String url) {
    return getOneTime(url, true);
  }

  public static HuobiQueryRespModel getOneTime(String url, boolean retries) {

    Call call = okHttpClient.newCall(new Builder().url(url).build());
    try {
      String r = call.execute().body().string();
      log.info(r);
      HuobiQueryRespModel queryRespModel = JSON.parseObject(r, HuobiQueryRespModel.class);
      if ("200".equals(queryRespModel.getCode())) {
        return queryRespModel;
      }
      log.error("retries={}, code={}, message={}", retries, queryRespModel.getCode(), queryRespModel.getMessage());
    } catch (IOException e) {
      log.error("retries={}", retries, e);
    }
    return retries ? getOneTime(url, false) : null;
  }
}
