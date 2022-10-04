package com.cts.stock.service.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.cts.stock.dto.CompanyDto;
import com.cts.stock.dto.ResponseData;
import com.cts.stock.dto.StockDto;
import com.cts.stock.entity.Stock;
import com.cts.stock.repository.StockRepository;
import com.cts.stock.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${auth.user.pass}")
	String pass;

	/**
	 * Adds the company stock.
	 *
	 * @param companyCode the company code
	 * @param stockPrice  the stock price
	 * @return the boolean
	 * @throws Exception the exception
	 */
	@Override
	public Stock addCompanyStock(String companyCode, Double stockDetails) throws Exception {
		log.info("Entering addCompanyStock Service");
		Stock stock = Stock.builder().companyCode(companyCode).price(stockDetails)
				.date(new Date()).timeStamp(new Date().getTime()).build();
		Stock save = stockRepository.save(stock);
		log.info("Exiting addCompanyStock Service");
		return save;
	}

	/**
	 * Filter stocks.
	 *
	 * @param companyCode the company code
	 * @param startDate   the start date
	 * @param endDate     the end date
	 * @return the company dto
	 * @throws Exception the exception
	 */
	@Override
	public CompanyDto filterStocks(String companyCode, String startDate, String endDate) throws Exception {
		log.info("Entering filterStocks Service");

		// SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date startDateFormatted = toDate(convertStringToLocalDate(startDate, "yyyy-MM-dd"));
		Date endDateFormatted = toDate(convertStringToLocalDate(endDate, "yyyy-MM-dd"));

		Calendar c = Calendar.getInstance();
		c.setTime(endDateFormatted);
		c.add(Calendar.DATE, 1);
		endDateFormatted = c.getTime();

		List<Stock> stocks = stockRepository.filterStock(companyCode, startDateFormatted, endDateFormatted);
		String companyUrl = "http://localhost:8081/api/v1.0/market/company/companyInfo/" + companyCode;
		log.info("companyUrl: {}", companyUrl);

		CompanyDto companyDetails = null;
		ResponseEntity<ResponseData> response = null;
		try {
			response = restTemplate.getForEntity(companyUrl, ResponseData.class);
			
			if (response.getBody().getData()!=null) {
				log.info("response: {}", response.getBody().getData());
				companyDetails = response.getBody().getData();

				List<Double> stockPrices = new ArrayList<Double>();
				Double maxPrice = null;
				Double minPrice = null;

				List<StockDto> stockDtos = new ArrayList<StockDto>();
				for (Stock stock : stocks) {
					StockDto stockDto = new StockDto();
					stockDto.setPrice(stock.getPrice());
					stockDto.setDate(stock.getDate());
					stockDto.setStockId(stock.getStockId());
					stockDto.setTimeStamp(stock.getTimeStamp());
					stockDtos.add(stockDto);

					if (minPrice == null || minPrice > stock.getPrice()) {
						minPrice = stock.getPrice();
					}
					if (maxPrice == null || maxPrice < stock.getPrice()) {
						maxPrice = stock.getPrice();
					}

					stockPrices.add(stock.getPrice());
				}

				OptionalDouble average = stockPrices.stream().mapToDouble(a -> a).average();
				companyDetails.setAvgStockPrice(average.isPresent() ? average.getAsDouble() : 0);
				companyDetails.setMinStockPrice(minPrice);
				companyDetails.setMaxStockPrice(maxPrice);

				companyDetails.setStocks(stockDtos);
			}
		} catch (Exception e) {
			log.error("Error in finding company with company code: {}", companyCode);
		}
		log.info("Exiting filterStocks Service");
		return companyDetails;
	}

	/**
	 * Fetch latest stock price.
	 *
	 * @param companyCode the company code
	 * @return the double
	 * @throws Exception the exception
	 */
	@Override
	public Double fetchLatestStockPrice(String companyCode) throws Exception {
		log.info("Entering fetchLatestStockPrice Service");
		Double latestStock = null;
		Date latestTimestamp = null;
		List<Stock> companyStocks = stockRepository.findByCompanyCode(companyCode);
		for (Stock companyStock : companyStocks) {
			if (latestTimestamp == null || latestTimestamp.before(companyStock.getDate())) {
				latestTimestamp = companyStock.getDate();
				latestStock = companyStock.getPrice();
			}
		}
		log.info("Exiting fetchLatestStockPrice Service");
		return latestStock;
	}

	/**
	 * Delete company stocks.
	 *
	 * @param companyCode the company code
	 * @return the boolean
	 * @throws Exception the exception
	 */
	@Override
	public Boolean deleteCompanyStocks(String companyCode) throws Exception {
		log.info("Entering deleteCompanyStocks Service");
		Boolean isSuccessful = true;

		List<Stock> companyStocks = stockRepository.findByCompanyCode(companyCode);
		for (Stock companyStock : companyStocks) {
			stockRepository.delete(companyStock);
		}

		log.info("Exiting deleteCompanyStocks Service");
		return isSuccessful;
	}

	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// private HttpEntity<String> getAuthToken() {
	// 	String token = null;

	// 	String url = "http://localhost:8081/api/v1.0/market/company/login";
	// 	HttpHeaders header = new HttpHeaders();
	// 	header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	// 	MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
	// 	body.add("username", "riya");
	// 	body.add("STOCK_PASS", pass);
	// 	HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(body, header);
	// 	try {
	// 		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
	// 		Map<String, String> responseBody = (Map<String, String>) response.getBody();
	// 		if (responseBody != null)
	// 			token = responseBody.get("access_token");
	// 	} catch (Exception e) {
	// 		log.error("Error in fetching token");
	// 	}

	// 	HttpHeaders headers = new HttpHeaders();
	// 	headers.add("Authorization", "Bearer " + token);
	// 	HttpEntity<String> getEntity = new HttpEntity<>(headers);
	// 	return getEntity;
	// }

	public static LocalDate convertStringToLocalDate(final String date, final String datePattern) {
		LocalDate localDate = null;
		if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(datePattern)) {
			final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
			localDate = LocalDate.parse(date, dateFormatter);
		}
		return localDate;
	}

	public static Date toDate(final LocalDate toConvert) {
		if (toConvert == null) {
			return null;
		}
		return Date.from(toConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
