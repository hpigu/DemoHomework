package com.example.demo.service;

import com.example.demo.feign.CoinFeign;
import com.example.demo.model.entity.CoinPO;
import com.example.demo.model.feign.response.BasicCoin;
import com.example.demo.model.feign.response.Bpi;
import com.example.demo.model.response.dm010101.DM010101Res;
import com.example.demo.model.repository.CoinRepository;
import com.example.demo.model.request.dm010201.DM010201Req;
import com.example.demo.model.request.dm010301.DM010301Req;
import com.example.demo.model.request.dm010401.DM010401Req;
import com.example.demo.model.response.dm010102.ConvertCoinDTO;
import com.example.demo.model.response.dm010102.DM010102Res;
import com.example.demo.model.response.dm010103.DM010103Res;
import com.example.demo.model.response.dm010201.DM010201Res;
import com.example.demo.model.response.dm010301.DM010301Res;
import com.example.demo.model.response.dm010401.DM010401Res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DemoService {

    private static final String Y = "Y";
    private static final String N = "N";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    private CoinFeign coinFeign;

    @Autowired
    private CoinRepository coinRepository;

    public DM010101Res queryCoin() {
        String coinRes = coinFeign.coin001();
        ObjectMapper om = new ObjectMapper();
        DM010101Res res = null;
        try {
            Map coinMap = om.readValue(coinRes, Map.class);
            res = om.convertValue(coinMap, DM010101Res.class);
        } catch (JsonProcessingException e) {
            log.error("readValue failed ", e);
        }
        return res;
    }

    public DM010103Res queryAllCoin(String currency) {
        DM010103Res dm010103Res = new DM010103Res();
        CoinPO coinPO = coinRepository.findByCurrency(currency);
        if(null != coinPO){
            ConvertCoinDTO convertCoinDTO = ConvertCoinDTO.builder()
                    .currency(coinPO.getCurrency())
                    .currencyChinese(coinPO.getCurrencyChinese())
                    .rate(coinPO.getRate())
                    .updateTime(sdf.format(coinPO.getUpdateTime())).build();
            dm010103Res.setCoin(convertCoinDTO);
        }
        return dm010103Res;
    }

    public DM010102Res queryCoinConvert() {
        DM010102Res dm010102Res = new DM010102Res();
        List<ConvertCoinDTO> convertCoinDTOList = new ArrayList<>();
        DM010101Res coinRes = this.queryCoin();
        Bpi bpi = coinRes.getBpi();
        List<BasicCoin> coinList = new ArrayList<>();
        coinList.add(bpi.getUsd());
        coinList.add(bpi.getEur());
        coinList.add(bpi.getGbp());
        List<String> codeList = coinList.stream().map(e -> e.getCode()).collect(Collectors.toList());
        List<CoinPO> coinPOList = coinRepository.findAllByCurrencyIn(codeList);
        for (CoinPO coin : coinPOList) {
            ConvertCoinDTO convertCoinDTO = ConvertCoinDTO.builder()
                    .currency(coin.getCurrency())
                    .currencyChinese(coin.getCurrencyChinese())
                    .rate(coin.getRate())
                    .updateTime(sdf.format(coin.getUpdateTime()))
                    .build();
            convertCoinDTOList.add(convertCoinDTO);
        }
        dm010102Res.setConvertCoinList(convertCoinDTOList);
        return dm010102Res;
    }

    public DM010201Res insertCoin(DM010201Req req) {
        DM010201Res dm010201Res = new DM010201Res();
        boolean exist = coinRepository.existsByCurrency(req.getCurrency());
        if (exist) {
            log.error("Currency exist");
            dm010201Res.setSaveResult(N);
        } else {
            try {
                CoinPO coinPo = new CoinPO();
                Date now = new Date();
                coinPo.setCurrency(req.getCurrency());
                coinPo.setCurrencyChinese(req.getCurrencyChinese());
                coinPo.setRate(req.getRate());
                coinPo.setCreateTime(now);
                coinPo.setUpdateTime(now);
                coinRepository.save(coinPo);
                dm010201Res.setSaveResult(Y);
            } catch (Exception e) {
                log.error("save coinPo Error", e);
                dm010201Res.setSaveResult(N);
            }
        }
        return dm010201Res;
    }

    public DM010301Res updateCoin(DM010301Req req) {
        DM010301Res dm0102301Res = new DM010301Res();
        CoinPO coinPO = coinRepository.findByCurrency(req.getCurrency());
        if (null != coinPO) {
            if (null != req.getRate()) {
                coinPO.setRate(req.getRate());
            }
            if (null != req.getCurrencyChinese()) {
                coinPO.setCurrencyChinese(req.getCurrencyChinese());
            }
            coinPO.setUpdateTime(new Date());
            try {
                coinRepository.save(coinPO);
                dm0102301Res.setUpdateResult(Y);
            } catch (Exception e) {
                log.error("save coinPo Error", e);
                dm0102301Res.setUpdateResult(N);
            }
        } else {
            log.error("Currency not exist");
            dm0102301Res.setUpdateResult(N);
        }
        return dm0102301Res;
    }

    public DM010401Res deleteCoin(DM010401Req req) {
        DM010401Res dm010401Res = new DM010401Res();
        Integer result = coinRepository.deleteByCurrency(req.getCurrency());
        if (result > 0) {
            dm010401Res.setDeleteResult(Y);
        } else {
            dm010401Res.setDeleteResult(N);
        }
        return dm010401Res;
    }
}
