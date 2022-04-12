package com.example.demo.model.response.dm010102;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DM010102Res {
    private List<ConvertCoinDTO> convertCoinList;
}
