package com.example.apollo.config;


import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.example.apollo.entity.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.inject.Singleton;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "bean")
@Component
public class ConfigBean {
    private int age;
    private String name;

    /**
     * 在字段上加@apollojsonValue示例，默认值指定为空列表-[]
     * jsonBeanProperty=[{"id":1,"name":"李四"},{"id":2,"name":"赵六"}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<User> anotherJsonBeans;
}
