package com.example.apollo.config;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.example.apollo.entity.User;
import com.google.gson.JsonObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class LhpConfig {

    //默认20
    @Value("${bean.age:20}")
    public int age;

    @Value("${bean.name:王五}")
    public String name;
    /**
     * 在字段上加@apollojsonValue示例，默认值指定为空列表-[]
     * jsonBeanProperty=[{"id":1,"name":"李四"},{"id":2,"name":"赵六"}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<User> anotherJsonBeans;

    @Value("${jsonBeanProperty:[]}")
    private String jsonBeanProperty;
}
