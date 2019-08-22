
package com.zqm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * list 使用
 * Date: 2019-08-20
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("list")
public class ListController {


    @RequestMapping("test")
    public void testList() {

    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        //指定位置添加元素
        list.add(1, 3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        //指定大小分隔list
        List<List<Integer>> aa = Lists.partition(list, 2);
        System.out.println("指定大小分隔list" + aa);

        List<Integer> retainList = new ArrayList<>();
        retainList.add(1);
        retainList.add(2);

        //指定位置添加list
        list.addAll(2, retainList);
        System.out.println("list指定位置添加list:" + list);

        //list只保留retainList有的
        list.retainAll(retainList);
        System.out.println("list只保留retainList有的" + list);

        //空判断
        System.out.println(list.isEmpty());
        //复制
        Object cloneList = ((ArrayList<Integer>) list).clone();
        System.out.println("复制返回的是Object类型:" + cloneList);
        //List<Integer> cloneList =  ((ArrayList<Integer>) list.clone();

        //创建一个空的list
        List<String> emptyList = Lists.newArrayList();
        List<String> stringList = new ArrayList<>();
        System.out.println("创建一个空的list：" + stringList);
        System.out.println("创建一个空的list：" + emptyList);
        emptyList.add("aa");

        System.out.println(emptyList);


        List<String> initString = Arrays.asList("dd");
        System.out.println("初始化创建list:" + initString);
        System.out.println("==================================");

        //倒序
        System.out.println(Lists.reverse(list));
        List<InnerClass> innerClassArrayList = Lists.newArrayList(new InnerClass(1, "dd"), new InnerClass(2, "cc"));
        System.out.println("innerclass：" + innerClassArrayList);
        //截取对象数组的摸一个属性集合
        System.out.println("对象集合中获取某一个属性的集合" +
                Lists.transform(innerClassArrayList, input -> input.getAnInt()));


        System.out.println("==================================");

        /**
         * 初始化数组 默认值为0 {0,0,0,0,0,0}
         */
        int[] array = new int[6];

        int[] initArray = {1, 2, 3, 4, 5, 6};

        //复制指定数组的指定长度
        int[] copyArray = Arrays.copyOf(initArray, 3);


    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InnerClass {
        private int anInt;
        private String b;
    }

}
