package com.zqm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: Lists使用笔记
 * @author:zqm
 */
public class ListsController {

    /**
     * Lists.transform 功能将一种类型的list转换成另一种list
     * 但是transform的结果会根据源的变化而变化
     */
    private static  void testTransform(){
        Map<String,String> map = Maps.newHashMap();
        map.put("1","test1");
        map.put("2","test2");
        Map<String,String> map2 = Maps.newHashMap();
        map2.put("1","test3");
        map2.put("4","test2");
        List<Map<String,String>> list2= Lists.newArrayList();
        list2.add(map);
        list2.add(map2);
        //将1对应的map的value 方法1
        List<String> list3=Lists.transform(list2, s->s.get("1"));

        //匿名函数   方法2
        List<String> list4=Lists.transform(list2, new Function<Map<String, String>, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Map<String, String> input) {
                return input.get("1");
            }
        });

//        accModelsRelationList list对象中的accid转成list
//        List<String> allAccIds = Lists.transform(accModelsRelationList, AccModelsRelation::getAccId);
//        去重
//        Set<String> accIds = new HashSet<>(allAccIds);


        list3.forEach(s -> System.out.println(s));
        list4.forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) {
        testTransform();
    }


}
