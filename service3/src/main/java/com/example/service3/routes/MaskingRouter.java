package com.example.service3.routes;

import com.example.service3.middlewareBeans.CsvBean;
import com.example.service3.processors.CsvProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MaskingRouter extends RouteBuilder {
    public static String FILE_DIR = "D:\\training\\Apache-Camel\\service3\\src\\main\\resources\\files\\";
    public static final String APPEND = "&fileExist=Append"; // add to the file
    public static final String noop = "&noop=true";

    @Override
    public void configure() throws Exception {
        from("file:" + FILE_DIR + "?fileName=file1.csv" + noop)
                .log("file has been read.")
                .log("From Log: [${body}].")
                .unmarshal().csv()
                .process(new CsvProcessor())
//                .log("file has been updated. [${body}]")
//                .marshal().csv()
                .to("file:" +FILE_DIR+"?fileName=file2.txt")
//                .bean(CsvBean.class)
                .log("file has been updated. [${body}]")
                .end();

    }
}

