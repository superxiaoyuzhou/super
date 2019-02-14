package com.example.apollo.controller;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.example.apollo.config.ConfigBean;
import com.example.apollo.config.LhpConfig;
import com.example.apollo.entity.User;
import com.example.apollo.mapper.UserMapper;
import com.example.apollo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApolloConfig("apollo1")
    private Config config;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private LhpConfig lhpConfig;

    @RequestMapping("insert")
    public Object insert() {
        User user = new User();
        user.setAddress("nnnn");
        user.setName("钱七");
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("name", "钱七");
        return userMapper.selectByCondition(condition);
    }

    @RequestMapping("/findAll")
    public Object findAll() {
        ConfigBean configBean = new ConfigBean();
        configBean.setAge(config.getIntProperty("bean.age", 20));
        configBean.setName(config.getProperty("bean.name", "老王"));
        return configBean;
    }

    @RequestMapping("/find1")
    public Object find1() {
        return configBean;
    }

    @RequestMapping("/find2")
    public Object find2() {
        return lhpConfig;
    }

    @RequestMapping("/find3")
    public Object find3() {
        return null;
    }

    @RequestMapping("/find4/{id}/{name}")
    public Object find4(@PathVariable(name = "id") String id, String name) {
        return null;
    }

    @RequestMapping("/find5")
    public Object find5(@RequestBody User user) {
        return userService.find5(user);
    }

    @RequestMapping("/find6")
    public Object find6(@RequestBody User user) {
        return userService.find6(user);
    }
}
