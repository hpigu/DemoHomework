package com.example.demo.model.response.dm010102;

import com.example.demo.model.entity.CoinPO;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class ConvertCoinDTO {
    private String currency;
    private String currencyChinese;
    private Float rate;
    private String updateTime;

    public ConvertCoinDTO(CoinPO coinPO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.currency = coinPO.getCurrency();
        this.currencyChinese = coinPO.getCurrencyChinese();
        this.rate = coinPO.getRate();
        this.updateTime = sdf.format(coinPO.getUpdateTime());
    }
}
