package com.cts.stock.service;

import org.slf4j.Logger;
import com.cts.stock.dto.CompanyDto;
import com.cts.stock.entity.Stock;

/**
 * The Interface StockService.
 */
public interface StockService {

	/**
	 * Adds the company stock.
	 *
	 * @param companyCode the company code
	 * @param stockPrice the stock price
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Stock addCompanyStock(String companyCode, Double stockDetails) throws Exception;

	/**
	 * Filter stocks.
	 *
	 * @param companyCode the company code
	 * @param startDate   the start date
	 * @param endDate     the end date
	 * @return the company dto
	 * @throws Exception the exception
	 */
	public CompanyDto filterStocks(String companyCode, String startDate, String endDate) throws Exception;

	/**
	 * Fetch latest stock price.
	 *
	 * @param companyCode the company code
	 * @return the double
	 * @throws Exception the exception
	 */
	public Double fetchLatestStockPrice(String companyCode) throws Exception;

	/**
	 * Delete company stocks.
	 *
	 * @param companyCode the company code
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deleteCompanyStocks(String companyCode) throws Exception;
}
