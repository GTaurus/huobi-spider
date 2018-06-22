package com.saicfc.huobi.controller;

import com.saicfc.huobi.models.ListQueryRespModel;
import com.saicfc.huobi.service.ListQueryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Log4j2
@CrossOrigin
public class ListQueryController {

  @Autowired
  private ListQueryService listQueryService;

  @RequestMapping("query")
  public ListQueryRespModel query(@RequestParam String coinType, @RequestParam String tradeDesc) {
    ListQueryRespModel model = listQueryService.query(coinType, tradeDesc);

    return model;
  }


}
