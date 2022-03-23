package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "COIN")
public class CoinPO {

    @Id
    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "RATE")
    Float rate;

    @Column(name = "CURRENCY_CHINESE")
    String currencyChinese;

    @Column(name = "CREATE_TIME")
    Date createTime;

    @Column(name = "UPDATE_TIME")
    Date updateTime;
}
