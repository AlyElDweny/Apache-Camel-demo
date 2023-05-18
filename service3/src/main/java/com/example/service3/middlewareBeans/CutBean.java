package com.example.service3.middlewareBeans;

import org.apache.camel.Handler;

public class CutBean {
    public String fromClient1 (String msg){
        return "From Client1: " + msg;
    }

    @Handler // we use this if this is the default method in the bean
    public String fromClient2 (String msg){
        return "From Client2: " + msg;
    }

    public String fromClient3 (String body, String header){
        return "From Client2: " + body + " and Header is: "+header;
    }
}
