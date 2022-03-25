package com.example.demo.model.response.dm010101;

import com.example.demo.model.feign.response.Bpi;
import com.example.demo.model.feign.response.Time;
import lombok.Data;

@Data
public class DM010101Res {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Bpi bpi;
}
