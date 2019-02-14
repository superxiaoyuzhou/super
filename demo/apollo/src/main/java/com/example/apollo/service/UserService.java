package com.example.apollo.service;



import com.example.apollo.entity.User;
import com.example.apollo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public User find1(User user) {
        return userMapper.selectOne(user);
    }

    public List<User> find2(User user) {
        return userMapper.selectAll();
    }

    public User find3(User user) {
        return userMapper.selectByPrimaryKey(user.getId());
    }

    public List<User> find4(User user) {

        return userMapper.selectByExample(null);
    }

    public List<User> find5(User user) {
        user.setAddress("aaa");
        user.setName("王五");
        user.setBirthday(new Date());
        int i = userMapper.insertUseGeneratedKeys(user);
        return null;
    }

    public List<User> find6(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id","6");
        return userMapper.selectByExample(example);
    }


}
