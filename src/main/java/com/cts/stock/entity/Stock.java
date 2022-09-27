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

	/** The stock id. */
	@Id
	private String stockId;

	private String stockName;

	/** The company code. */
	private String companyCode;

	/** The date. */
	private Date date;

	/** The price. */
	private Double price;

	/** The time stamp. */
	private Long timeStamp;

}
