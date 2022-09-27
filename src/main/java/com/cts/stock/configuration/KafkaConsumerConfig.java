// package com.cts.stock.configuration;

// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// import com.cts.stock.Constant.StockConstants;

// import lombok.Generated;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Generated
// @Service
// public class KafkaConsumerConfig {

// 	@KafkaListener(topics = "message", groupId = StockConstants.GROUP_ID)
// 	public void consume(String message) {
// 		//System.out.println("message received" + message);
// 		log.info("MESSAGE RECEIVED BY CONSUMER -> " +message);
// 	}
// }
