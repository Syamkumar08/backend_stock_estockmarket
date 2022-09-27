// package com.cts.stock.configuration;

// import org.apache.kafka.clients.admin.NewTopic;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Service;

// import com.cts.stock.Constant.StockConstants;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Configuration
// @Service
// public class KafkaProducerConfig {

// 	@Autowired
// 	private KafkaTemplate<String, String> kafkaTemplate;

// 	public void sendMessage(String message) {
// 		log.info("MESSAGE SENT BY PRODUCER -> " +message);
// 		this.kafkaTemplate.send(StockConstants.TOPIC_NAME, StockConstants.TOPIC_NAME, message);
// 	}

// 	@Bean
// 	public NewTopic createTopic() {
// 		return new NewTopic(StockConstants.TOPIC_NAME, 3, (short) 1);
// 	}

// }
