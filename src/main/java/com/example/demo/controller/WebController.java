package com.example.demo.controller;


import com.example.demo.kafka.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class WebController {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    AtomicInteger ai = new AtomicInteger();

   // @Autowired
    MsgProducer producer;

    @RequestMapping("index")
    public String index(){
        return "ok";
    }



    @RequestMapping("kafka")
    public String kafka(){
        producer.send();
        return "true";
    }


    @RequestMapping("save")
    private String save(){

        npjt.getJdbcOperations().update("INSERT  into savetest ( name ) VALUE (?)",ai.incrementAndGet()+"");


        return "true";
    }






    private List<char[]> list = new ArrayList<>();
    @RequestMapping("memory")
    public String memory( int length ){

        while (true){
            list.add(new char[length]);
        }

    }

}
