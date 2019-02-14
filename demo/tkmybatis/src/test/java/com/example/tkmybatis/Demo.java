package com.example.tkmybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tkmybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class Demo {

    @Test
    public void demo(){
        User user = new User();
        user.setId(1);
        user.setName("中金");
        String s = JSON.toJSONString(user);
        log.info("对象转String" + s);
        Object parse = JSON.parse(s);
        log.info("String转map集合" + parse.toString());
        Object parse2 = JSONObject.parse(s);
        log.info("String转对象" + parse2.getClass().getName());
        Object parse1 = JSONArray.toJSON(user);
        log.info("" + parse1);
    }
}
