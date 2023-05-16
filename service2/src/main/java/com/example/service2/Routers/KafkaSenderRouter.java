package com.example.service2.Routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


import static org.apache.camel.LoggingLevel.ERROR;

//@Component
public class KafkaSenderRouter extends RouteBuilder {

    final String KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:29092";

    @Override
    public void configure() throws Exception {
        from("timer://test-kafka?period=10000")
                .bean(StockPrice.class)
                .log(ERROR, "new stock price is => [${body}]")
                .toF(KAFKA_ENDPOINT, "stock-live");
    }

    private class StockPrice {
        public int getStockPrice() {
            int rand = (int)(Math.random() * 100);
            return rand;
        }
    }
}
