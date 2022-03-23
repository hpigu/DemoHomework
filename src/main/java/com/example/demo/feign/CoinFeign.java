package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "coinSerivce", url = "https://api.coindesk.com/v1/bpi")

public interface CoinFeign {

    @GetMapping(path = "/currentprice.json" , consumes = "application/javascript")
    String coin001();
}
