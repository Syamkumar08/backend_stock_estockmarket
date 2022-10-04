package com.cts.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stock.Constant.StockConstants;
import com.cts.stock.dto.CompanyDto;
import com.cts.stock.dto.ResponseMessage;
import com.cts.stock.dto.StockResponse;
import com.cts.stock.entity.Stock;
import com.cts.stock.service.StockService;

import lombok.Generated;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@Generated
@CrossOrigin(origins = "http://localhost:4200")
public class StockController {

    private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

    private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

    /**
     * The stock service.
     */
    @Autowired
    StockService stockService;
    
    /**
     * Adds the company stock.
     *
     * @param companyCode the company code
     * @param stockPrice  the stock price
     * @return the response entity
     */
    @PostMapping(value = "/add/{companycode}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockResponse<Stock>> addCompanyStock(@RequestBody Double stockPrice,
            @PathVariable("companycode") String companyCode) {
        applicationLog.info("Entering addCompanyStock Controller");
        StockResponse<Stock> response = new StockResponse<>();
        ResponseMessage message = new ResponseMessage();
        try {
            Stock isSuccessful = stockService.addCompanyStock(companyCode, stockPrice);
            response.withData(isSuccessful);
            applicationLog.info(StockConstants.EXITING_ADD_COMPANY_STOCK_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            errorLog.error("Error in adding Stocks. Error: {}", e.getMessage());
            message.setCode(StockConstants.STOCK_ADD_FAILED);
            message.setDescription("Error in adding stock: " + e.getMessage());
            response.withData(null);
            response.withMessage(message);
            applicationLog.info(StockConstants.EXITING_ADD_COMPANY_STOCK_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Filter stocks.
     *
     * @param companyCode the company code
     * @param startDate   the start date
     * @param endDate     the end date
     * @return the response entity
     */
    @GetMapping(value = "/get/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<StockResponse<CompanyDto>> filterStocks(@PathVariable("companycode") String companyCode,
            @PathVariable("startdate") String startDate, @PathVariable("enddate") String endDate) {
        applicationLog.info("Entering filterStocks Controller");
        StockResponse<CompanyDto> response = new StockResponse<>();
        ResponseMessage message = new ResponseMessage();
        try {
            CompanyDto company = stockService.filterStocks(companyCode, startDate, endDate);
            if (company != null && !company.getStocks().isEmpty()) {
                message.setCode(StockConstants.FILTER_STOCK_SUCCESS);
                message.setDescription("Stocks Fetched");
                response.withData(company);
                response.withMessage(message);
                applicationLog.info(StockConstants.EXITING_FILTER_STOCKS_CONTROLLER);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                message.setCode(StockConstants.NO_STOCK_FOUND);
                message.setDescription("No Stock found");
                response.withData(null);
                response.withMessage(message);
                applicationLog.info(StockConstants.EXITING_FILTER_STOCKS_CONTROLLER);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            errorLog.error(StockConstants.FILTER_STOCK_ERROR + ": {}", e.getMessage());
            message.setCode(StockConstants.FILTER_STOCK_FAILED);
            message.setDescription(StockConstants.FILTER_STOCK_ERROR + ": " + e.getMessage());
            response.withData(null);
            response.withMessage(message);
            applicationLog.info(StockConstants.EXITING_FILTER_STOCKS_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetch latest stock price.
     *
     * @param companyCode the company code
     * @return the response entity
     */
    @GetMapping(value = "/get/stockPrice/{companycode}")
    public ResponseEntity<StockResponse> fetchLatestStockPrice(
            @PathVariable("companycode") String companyCode) {
        applicationLog.info("Entering fetchLatestStockPrice Controller");
        StockResponse<Double> response = new StockResponse<>();
        ResponseMessage message = new ResponseMessage();
        try {
            Double companyStockPrice = stockService.fetchLatestStockPrice(companyCode);
            if (companyStockPrice != null) {
                message.setCode(StockConstants.LATEST_STOCK_PRICE_FETCHED);
                message.setDescription("Latest stock price fetched");
                response.withData(companyStockPrice);
                response.withMessage(message);
                applicationLog.info(StockConstants.EXITING_FETCH_LATEST_STOCK_PRICE_CONTROLLER);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                message.setCode(StockConstants.NO_STOCK_FOUND);
                message.setDescription("No Stock found");
                response.withData(null);
                response.withMessage(message);
                applicationLog.info(StockConstants.EXITING_FETCH_LATEST_STOCK_PRICE_CONTROLLER);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            errorLog.error(StockConstants.STOCK_FETCH_ERROR + "{}", e.getMessage());
            message.setCode(StockConstants.LATEST_STOCK_FETCH_FAILED);
            message.setDescription(StockConstants.STOCK_FETCH_ERROR + e.getMessage());
            response.withData(null);
            response.withMessage(message);
            applicationLog.info(StockConstants.EXITING_FETCH_LATEST_STOCK_PRICE_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete company stocks.
     *
     * @param companyCode the company code
     * @return the response entity
     */
    @DeleteMapping(value = "/delete/{companycode}")
    public ResponseEntity<StockResponse<Boolean>> deleteCompanyStocks(@PathVariable("companycode") String companyCode) {
        applicationLog.info("Entering deleteCompanyStocks Controller");
        StockResponse<Boolean> response = new StockResponse<>();
        ResponseMessage message = new ResponseMessage();
        try {
            Boolean isSuccessful = stockService.deleteCompanyStocks(companyCode);

            message.setCode(StockConstants.COMPANY_STOCK_DELETED);
            message.setDescription("Company stocks deleted with company code: " + companyCode);
            response.withData(isSuccessful);
            response.withMessage(message);
            applicationLog.info(StockConstants.EXITING_DELETE_COMPANY_STOCK_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            errorLog.error(StockConstants.STOCK_DELETE_ERROR + "{}", e.getMessage());
            message.setCode(StockConstants.COMPANY_STOCK_DELETE_FAILED);
            message.setDescription(StockConstants.STOCK_DELETE_ERROR + e.getMessage());
            response.withData(null);
            response.withMessage(message);
            applicationLog.info(StockConstants.EXITING_DELETE_COMPANY_STOCK_CONTROLLER);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
