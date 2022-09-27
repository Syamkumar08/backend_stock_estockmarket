package com.cts.stock.dto;

import java.util.List;

/**
 * The Class CompanyDto.
 */
public class CompanyDto {

	/** The company id. */
	private String companyId;
	
	/** The company code. */
	private String companyCode;
	
	/** The company name. */
	private String companyName;
	
	/** The company ceo. */
	private String companyCeo;
	
	/** The company turnover. */
	private String companyTurnover;
	
	/** The company website. */
	private String companyWebsite;
	
	/** The stock exchange. */
	private String stockExchange;
	
	/** The stocks. */
	private List<StockDto> stocks;
	
	/** The max stock price. */
	private Double maxStockPrice;
	
	/** The min stock price. */
	private Double minStockPrice;
	
	/** The avg stock price. */
	private Double avgStockPrice;

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId the new company id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * Gets the company code.
	 *
	 * @return the company code
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * Sets the company code.
	 *
	 * @param companyCode the new company code
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company ceo.
	 *
	 * @return the company ceo
	 */
	public String getCompanyCeo() {
		return companyCeo;
	}

	/**
	 * Sets the company ceo.
	 *
	 * @param companyCeo the new company ceo
	 */
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}

	/**
	 * Gets the company turnover.
	 *
	 * @return the company turnover
	 */
	public String getCompanyTurnover() {
		return companyTurnover;
	}

	/**
	 * Sets the company turnover.
	 *
	 * @param companyTurnover the new company turnover
	 */
	public void setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	/**
	 * Gets the company website.
	 *
	 * @return the company website
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	/**
	 * Sets the company website.
	 *
	 * @param companyWebsite the new company website
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	/**
	 * Gets the stock exchange.
	 *
	 * @return the stock exchange
	 */
	public String getStockExchange() {
		return stockExchange;
	}

	/**
	 * Sets the stock exchange.
	 *
	 * @param stockExchange the new stock exchange
	 */
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	/**
	 * Gets the stocks.
	 *
	 * @return the stocks
	 */
	public List<StockDto> getStocks() {
		return stocks;
	}

	/**
	 * Sets the stocks.
	 *
	 * @param stocks the new stocks
	 */
	public void setStocks(List<StockDto> stocks) {
		this.stocks = stocks;
	}

	/**
	 * Gets the max stock price.
	 *
	 * @return the max stock price
	 */
	public Double getMaxStockPrice() {
		return maxStockPrice;
	}

	/**
	 * Sets the max stock price.
	 *
	 * @param maxStockPrice the new max stock price
	 */
	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	/**
	 * Gets the min stock price.
	 *
	 * @return the min stock price
	 */
	public Double getMinStockPrice() {
		return minStockPrice;
	}

	/**
	 * Sets the min stock price.
	 *
	 * @param minStockPrice the new min stock price
	 */
	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	/**
	 * Gets the avg stock price.
	 *
	 * @return the avg stock price
	 */
	public Double getAvgStockPrice() {
		return avgStockPrice;
	}

	/**
	 * Sets the avg stock price.
	 *
	 * @param avgStockPrice the new avg stock price
	 */
	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
}
