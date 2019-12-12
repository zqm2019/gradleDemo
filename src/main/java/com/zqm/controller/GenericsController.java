/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.service.GenericService;
import com.zqm.service.impl.GenericServiceImpl1;
import com.zqm.service.impl.GenericServiceImpl2;

/**
 * 泛型方法 泛型接口 泛型类 测试
 * Date: 2019-11-12
 * <p>
 * <p>
 * List ：完全没有类型限制和赋值限定。
 * List<Object> ：看似用法与List一样，但是在接受其他泛型赋值时会出现编译错误。
 * List<?>：是一个泛型，在没有赋值前，表示可以接受任何类型的集合赋值，但赋值之后不能往里面随便添加元素，但可以remove和clear，并非immutable(不可变)集合。
 * List<?>一般作为参数来接收外部集合，或者返回一个具体元素类型的集合，也称为通配符集合。
 * <p>
 * 有界通配符
 * <? extends T> ：(put功能受限，适用于消费集合场景)可以赋值给任意T及T的子类集合，上界为T，取出来的类型带有泛型限制，向上强制转型为T。
 * null 可以表示任何类型，所以null除外，任何元素都不得添加进<? extends T>集合内。
 * <? super T> : (get功能，生产者集合)可以复制T及任何T的父类集合，下界为T。再生活中，投票选举类似于<? super T>的操作。
 * 选举投票时，你只能往里投票，取数据时，根本不知道时是谁的票，相当于泛型丢失。
 * <p>
 * List<T>和List<?>的区别：使用场景如下
 * <p>
 * 第一，声明一个泛型类或泛型方法。
 * 第二，使用泛型类或泛型方法。
 * 类型参数“<T>”主要用于第一种，声明泛型类或泛型方法。 无界通配符“<?>”主要用于第二种，使用泛型类或泛型方法。
 * 泛型类必须是使用“<T>”
 * <T>声明泛型方法
 *
 * @author zhaqianming
 */
@RestController
public class GenericsController {

    @Autowired
    private List<GenericService> genericService;

//    private static Map



//    public static void method(List<String> list){
//        System.out.println("string list");
//    }
//    public static void method(List<Integer> list){
//        System.out.println("integer list");
//    }

//    public static String method(List<String> list){
//        System.out.println("string list");
//        return "";
//    }
//    public static Integer method(List<Integer> list){
//        System.out.println("integer list");
//        return 1;
//    }

    public static void main(String[] args) {
        GenericServiceImpl1 genericService = new GenericServiceImpl1();
        genericService.show("dddd");
        GenericServiceImpl2 genericServiceImpl2 = new GenericServiceImpl2();
        genericServiceImpl2.show(1);

        if (true) {
            System.out.println("true");
        } else {
            System.out.println(false);
        }

        /**
         * 上述代码反编译之后的
         *   System.out.println("true");
         */
        List<String> list = new ArrayList<>();

        list.add("dfsaf");
        List<Integer> listinz = new ArrayList<>();
        listinz.add(1);
        listinz.add(2);
        for (Integer a : listinz) {
            System.out.println(a);
        }

        System.out.println(list.get(0));
        /** 上述代码反编译文件
         * public static void main(String[] args) {
         *       ArrayList list = new ArrayList();
         *       list.add("dfsaf");
         *       ArrayList listinz = new ArrayList();
         *       listinz.add(Integer.valueOf(1));
         *       listinz.add(Integer.valueOf(2));
         *       Iterator var3 = listinz.iterator();
         *
         *       while(var3.hasNext()) {
         *          Integer a = (Integer)var3.next();
         *          System.out.println(a);
         *       }
         *
         *       System.out.println((String)list.get(0));
         */
//        List<String> stringList = new ArrayList<>();
//
//        getElementForList(stringList);
//        System.out.println();
//        stringList.add("w");
//        getElementForList(stringList);
//        System.out.println();
//
//        List<Integer> integers = Arrays.asList(1, 3);
//        getElementForList(integers);
//        System.out.println(getSubList(integers));
//        System.out.println(getSubList(stringList));
//        System.out.println(getList(stringList));
//        System.out.println(getList(integers));
//        System.out.println(getSubListforT(stringList));
//        System.out.println(getSubListforT(integers));

    }


    public static void getElementForList(List<?> inputArray) {
        System.out.println(inputArray.size());
    }

    //无界通配符
    public static List<?> getSubList(List<?> inputArray) {


        //System.out.println(inputArray.size());
        //<?>的各种坑 get(
        Object object = inputArray.get(0);

        return inputArray.subList(0, 1);
    }

    //无界通配符 各种坑 get防范是个object， 添加不了
    public static List<?> getSubListDetail(List<?> inputArray) {
        //都是错误的示范
//        inputArray.add("dd");
//        inputArray.add(111);

        //System.out.println(inputArray.size());
        //<?>的各种坑 get(
        Object object = inputArray.get(0);

        return inputArray.subList(0, 1);
    }

    public static List getList(List inputArray) {

        //System.out.println(inputArray.size());
        return inputArray.subList(0, 1);
    }

    //泛型方法 <T>声明泛型方法
    public static <T> List<T> getSubListforT(List<T> inputArray) {

        //System.out.println(inputArray.size());
        return inputArray.subList(0, 1);
    }


    //定义泛型类，此处不能使用？ <T>声明泛型类的类型参数
    public class Animals<T> {

        //这里写成List<T>为了表示和Box<T>类型参数保持一致
        private List<T> animals;


        //使用类型参数的目的：是一种约束，为了保障oneAnimal和twoAnimal都是同一个类型
        //Animals<Cat>,代表两个animals都是Cat类型
        //Animals<Dag>,代表两个animals都是Dag类型

        private T oneAnimal;
        private T twoAnimal;

        public List<T> getAnimals() {
            return animals;
        }

        public void setAnimals(List<T> animals) {
            this.animals = animals;
        }

        public T getOneAnimal() {
            return oneAnimal;
        }

        public void setOneAnimal(T oneAnimal) {
            this.oneAnimal = oneAnimal;
        }

        public T getTwoAnimal() {
            return twoAnimal;
        }

        public void setTwoAnimal(T twoAnimal) {
            this.twoAnimal = twoAnimal;
        }
    }

    public class Dog extends Animals {

    }


    public class Cat extends Animals {

    }

    void testAinmals() {
        Animals<Dog> animals = new Animals<>();
        animals.setOneAnimal(new Dog());
//        animals.setTwoAnimal(new Cat());  //禁止添加非Dog类型

    }

    //食物基类
    public class Fruit {

    }

    public class Apple extends Fruit {
    }


    //泛型盘子类
    public class Plate<T> {
        private T item;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }


}
