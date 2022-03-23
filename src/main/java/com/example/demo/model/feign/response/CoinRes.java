package com.example.demo.model.feign.response;

import lombok.Data;

@Data
public class CoinRes {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Bpi bpi;
}
