package com.cts.stock.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
@ToString
@Getter
@Setter
public class StockDetails {

    private String stockName;
    private Double stockPrice;
}
