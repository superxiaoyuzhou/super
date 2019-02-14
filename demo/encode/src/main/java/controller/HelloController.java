package com.example.demo.controller;

import com.example.demo.dto.Request;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestBody Request request){
        String s = request.get("aaa");
        return s;
    }

    @RequestMapping("/hello11")
    public String hello11(@RequestBody Request request){

        return "11";
    }
}
