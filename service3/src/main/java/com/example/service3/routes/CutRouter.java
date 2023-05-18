package com.example.service3.routes;

import com.example.service3.middlewareBeans.CutBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.camel.LoggingLevel.INFO;
import static org.apache.camel.component.bean.BeanConstants.BEAN_METHOD_NAME;

//@Component
public class CutRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:demoTimer?period=10000")
                .setBody(e->new Date())
                .setHeader("myHeader", ()-> "aly's header")
//                .setHeader(BEAN_METHOD_NAME, ()->"fromClient1") // it takes priority to run

//                HERE: 3 ways to invoke a bean
//                .to("bean:com.example.service3.middlewareBeans.CutBean" +
//                        "?method=fromClient3(${body}, ${header.myHeader})")
//                =
//                .bean(new CutBean())
//                =
                .bean(CutBean.class, "fromClient1")
                .log(INFO, "From Log: [${body}]");
    }
}
