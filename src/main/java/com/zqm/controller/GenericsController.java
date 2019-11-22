/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * 泛型方法 泛型接口 泛型类 测试
 * Date: 2019-11-12
 *
 * @author zhaqianming
 */
@RestController
public class GenericsController {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        getElementForList(stringList);
        System.out.println();
        stringList.add("w");
        getElementForList(stringList);
        System.out.println();

        List<Integer> integers = Arrays.asList(1,3);
        getElementForList(integers);
        getSubList(integers);
    }


    public static void getElementForList(List<?> inputArray) {
        System.out.println(inputArray.size());
    }

    public static List<?> getSubList(List<?> inputArray) {

        System.out.println(inputArray.size());
        return inputArray.subList(0,1);
    }
}
