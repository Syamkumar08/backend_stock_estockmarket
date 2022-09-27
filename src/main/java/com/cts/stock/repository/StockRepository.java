package com.cts.stock.repository;

import com.cts.stock.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface StockRepository extends MongoRepository<Stock, String> {

    /**
     * Filter stock.
     *
     * @param companyCode the company code
     * @param startDate the start date
     * @param endDate the end date
     * @return the list
     */
    @Query("{'companyCode': ?0, 'date': {$gte: ?1}, 'date': {$lte: ?2}}")
    public List<Stock> filterStock(String companyCode, Date startDate, Date endDate);

    /**
     * Find by company code.
     *
     * @param companyCode the company code
     * @return the list
     */
    public List<Stock> findByCompanyCode(String companyCode);
}

