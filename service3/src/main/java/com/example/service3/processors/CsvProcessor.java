package com.example.service3.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CsvProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<List<String>> inputMatrix = (List<List<String>>) exchange.getIn().getBody();

        int INPUT_COL_NUM = inputMatrix.get(0).size(); // 3
        int INPUT_ROW_NUM = inputMatrix.size(); // 2

        ArrayList<ArrayList<String>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < INPUT_COL_NUM; i++) {
            ArrayList<String> OUTPUT_ROW = new ArrayList<>();

            for (List<String> matrix : inputMatrix) {
                OUTPUT_ROW.add(matrix.get(i));
            }
            outputMatrix.add(OUTPUT_ROW);
        }

        // convert output list to string
        StringBuilder outputTxt= new StringBuilder();
        for (ArrayList<String> matrix : outputMatrix) {
            outputTxt.append(matrix.toString()).append("\n");
        }



        exchange.getMessage().setBody(outputTxt);
}}
