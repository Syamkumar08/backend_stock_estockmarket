package com.cts.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private Long companyId;

    private String companyCode;

    private String companyName;

    private String companyCEO;

    private long companyTurnover;

    private String companyWebsite;

    private String stockExchange;
}
