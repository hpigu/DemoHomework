package com.example.demo.model.response.dm010102;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConvertCoinDTO {
    private String currency;
    private String currencyChinese;
    private Float rate;
    private String updateTime;
}
