package com.example.service1.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FirstRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://test?period=5000")
                .setBody(simple("Hi There!!!!"))
                .to("log:test");
    }
}
