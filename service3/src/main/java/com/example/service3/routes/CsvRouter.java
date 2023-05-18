package com.example.service3.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

//@Component
public class CsvRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // Read CSV files from the input directory
        from("file:/src/main/resources/files/dataSample.csv")
                .unmarshal().csv()
//                .split(body())

                // Process each CSV record
                .process(exchange -> {
                    Map<String, Object> record = exchange.getIn().getBody(Map.class);

                    // Modify the CSV record data
                    record.put("newField", "someValue");

                    // Set the modified record as the body
                    exchange.getIn().setBody(record);
                })

                // Marshal the modified records into a CSV file
                .marshal().csv()
                .to("file:/src/main/resources/files/output?fileName=output.csv");
    }
}