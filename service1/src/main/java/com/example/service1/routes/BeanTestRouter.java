package com.example.service1.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class BeanTestRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("timer:test-bean-timer?period=10000")
                .bean("calculator","goodbyeMsg")
                .to("log:test-bean-logger");
    }
}

@Component
class Calculator{
    public String welcomeMsg(){
        return "Welcome from calculator class";
    }

    public String goodbyeMsg(){
        return "Goodbye from calculator class";
    }
}