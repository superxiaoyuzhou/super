package com.example.tkmybatis.controller;

import com.example.tkmybatis.entity.User;
import com.example.tkmybatis.mapper.UserMapper;
import com.example.tkmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("insert")
    public Object insert(){
        User user = new User();
        user.setName("钱七");
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("name","钱七");
        return userMapper.selectByCondition(condition);
    }

    @RequestMapping("/findAll")
    public List<User> findAll(){
        List<User> list = userService.findAll();
        return list;
    }

    @RequestMapping("/findById/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @RequestMapping("/find1")
    public Object find1(@RequestBody User user){

        return userMapper.updateByPrimaryKey(user);
    }
    @RequestMapping("/find2")
    public Object find2(@RequestBody User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
    @RequestMapping("/find3")
    public Object find3(){
        return new ArrayList<>();
    }
    @RequestMapping("/find4/{id}/{name}")
    public Object find4(@PathVariable(name = "id") String id,String name){
        return id + "和" + name;
    }
    @RequestMapping("/find5")
    public Object find5(@RequestBody User user){
        return userService.find5(user);
    }
    @RequestMapping("/find6")
    public Object find6(@RequestBody User user){
        return userService.find6(user);
    }
}
