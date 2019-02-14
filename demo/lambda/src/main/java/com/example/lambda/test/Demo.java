package com.example.lambda.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args){

        Arrays.asList("a","b","c").forEach(s -> System.out.println(s));
        //上面这个代码中的参数s的类型是由编译器推理得出的，你也可以显式指定该参数的类型，例如：
        //Arrays.asList("a","b","c").forEach((String s) -> System.out.println(s));
        //Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体，例如：
        Arrays.asList("a","b","c").forEach(s -> {
            System.out.print(s);
            System.out.print(s);
        });
        System.out.println();
        System.out.println("=======================================================");
        //Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的）,
        // 例如下列两个代码块的效果完全相同：
        final String separator = ",";
        Arrays.asList("a","b","c").forEach(s -> System.out.print(s + separator));
        System.out.println();
        System.out.println("=======================================================");
        //如果Lambda表达式中的语句块只有一行，则可以不用使用return语句
        List<String> list = Arrays.asList("c","a", "d", "b");
        list.sort((e1, e2) -> e1.compareTo(e2));
        System.out.println(list);
        list.sort( ( e1, e2 ) -> {
            int result = e2.compareTo( e1 );
            return result;
        } );
        System.out.println(list);



    }
}

/**
 * //lambda的设计者们为了让现有的功能与Lambda表达式良好兼容，考虑了很多方法，于是产生了函数接口这个概念。函数接口指的是只有一个函数的接口，这样的接口可以隐式转换为Lambda表达式.
 // java.lang.Runnable和java.util.concurrent.Callable是函数式接口的最佳例子。在实践中，函数式接口非常脆弱：只要某个开发者在该接口中添加一个函数，则该接口就不再是函数式接口进而导致编译失败。
 // 为了克服这种代码层面的脆弱性，并显式说明某个接口是函数式接口，Java 8 提供了一个特殊的注解@FunctionalInterface（Java 库中的所有相关接口都已经带有这个注解了），举个简单的函数式接口的定义：
 */
@FunctionalInterface
interface Functional {
    void method();
}
/**
 * 不过有一点需要注意，默认方法和静态方法不会破坏函数式接口的定义，因此如下的代码是合法的。
 */
@FunctionalInterface
interface FunctionalDefaultMethods {
    void method();

    static String staticMehtod(String str){
        return str + "静态方法";
    }

    default void defaultMethod() {
        System.out.println("默认方法");
    }
}