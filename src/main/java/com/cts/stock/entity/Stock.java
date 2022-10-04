package com.cts.stock.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;

/**
 * The Class StockDao.
 */
@Document("Stock")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

	@Id
	private String stockId;

	private String companyCode;

	private Date date;

	private Double price;

	private Long timeStamp;

}
