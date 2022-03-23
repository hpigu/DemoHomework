package com.example.demo.model.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bpi {

    @JsonProperty("USD")
    private BasicCoin usd;

    @JsonProperty("GBP")
    private BasicCoin gbp;

    @JsonProperty("EUR")
    private BasicCoin eur;
}
