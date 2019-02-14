package com.example.lambda.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    public static void main(String[] args) {
        //初始化list集合
        List<String> list = new ArrayList<String>();
        list.add("测试数据1");
        list.add("测试数据2");
        list.add("测试数据3");
        list.add("测试数据12");
        //使用λ表达式遍历集合
        list.forEach((String s) -> System.out.println(s));
        System.out.println("=====================================");
        //结合Predicate使用和过滤条件筛选元素
        Predicate<String> contain1 = n ->n.contains("1");
        Predicate<String> contain2 = n -> n.contains("2");
        //根据条件遍历集合
        list.stream().filter(contain1).forEach(n -> System.out.println(n));
        System.out.println("----------------------------------------");
        list.stream().filter(s -> contain1.test(s)).forEach(s -> System.out.println(s));
        list.stream().filter(contain1.and(contain2)).forEach(n -> System.out.println(n));
        list.stream().filter(contain1.or(contain2)).forEach(n -> System.out.println(n));
        System.out.println("=====================================");
        //将过滤后的元素重新放到一个集合中
        List<String> newList = list.stream().filter(contain1.and(contain2)).collect(Collectors.toList());
        newList.forEach(s -> System.out.println(s));
    }

}
