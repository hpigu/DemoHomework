package com.example.demo.controller;

import com.example.demo.model.feign.response.CoinRes;
import com.example.demo.model.request.dm010201.DM010201Req;
import com.example.demo.model.request.dm010301.DM010301Req;
import com.example.demo.model.request.dm010401.DM010401Req;
import com.example.demo.model.response.dm010102.DM010102Res;
import com.example.demo.model.response.dm010103.DM010103Res;
import com.example.demo.model.response.dm010201.DM010201Res;
import com.example.demo.model.response.dm010301.DM010301Res;
import com.example.demo.model.response.dm010401.DM010401Res;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    private static final String MEDIA_TYPE = "application/json;charset=UTF-8";
    @Autowired
    DemoService demoService;

    @GetMapping(value = "/DM010101", produces = MEDIA_TYPE)
    public CoinRes dm010101() {
        return demoService.queryCoin();
    }

    @GetMapping(value = "/DM010102", produces = MEDIA_TYPE)
    public DM010102Res dm010102() {
        return demoService.queryCoinConvert();
    }

    @GetMapping(value = "/DM010103", produces = MEDIA_TYPE)
    public DM010103Res dm010103(@RequestParam String currency) {
        return demoService.queryAllCoin(currency);
    }

    @PostMapping(value = "DM010201", produces = MEDIA_TYPE)
    @ResponseBody
    public DM010201Res dm010201(@RequestBody DM010201Req req) {
        return demoService.insertCoin(req);
    }

    @PostMapping(value = "DM010301", produces = MEDIA_TYPE)
    @ResponseBody
    public DM010301Res dm010301(@RequestBody DM010301Req req) {
        return demoService.updateCoin(req);
    }

    @PostMapping(value = "DM010401", produces = MEDIA_TYPE)
    @ResponseBody
    public DM010401Res dm010401(@RequestBody DM010401Req req) {
        return demoService.deleteCoin(req);
    }
}
