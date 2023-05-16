package com.example.service1.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.camel.LoggingLevel.ERROR;

@Component
public class KafkaReceiverRouter extends RouteBuilder {

    final String KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:29092";

    @Override
    public void configure() throws Exception {
        fromF(KAFKA_ENDPOINT, "stock-live")
                .log(ERROR, "[${header.kafka.OFFSET}] [${body}]")
                .bean(StockPriceEnricher.class)
                .toF(KAFKA_ENDPOINT, "stock-audit");
    }

    private class StockPriceEnricher {
        public String enrichStockPrice(String stockPrice) {
            return stockPrice + "," + new Date();
        }
    }
}
//DOCKER-container shell$ kafka-console-producer --broker-list localhost:29092 --topic stock-live
//DOCKER-container shell$ kafka-console-consumer --bootstrap-server localhost:9092 --topic stock-audit