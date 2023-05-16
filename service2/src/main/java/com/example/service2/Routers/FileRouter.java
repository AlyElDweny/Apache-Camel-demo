package com.example.service2.Routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://D:\\training\\Apache-Camel\\service2\\files\\input")
                .log("${body}")
                .bean(ModifyFile.class)
                .to("file://D:\\training\\Apache-Camel\\service2\\files\\output");
    }

    private class ModifyFile{
        public String enrichFile(String content){
            return content + " , " + "modifiedAt : " + new Date();
        }
    }
}
