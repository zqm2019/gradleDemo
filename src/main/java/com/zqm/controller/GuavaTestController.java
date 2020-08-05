package com.zqm.controller;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

/**
 * @describe:
 * @author:zqm
 */
public class GuavaTestController {

    /**
     * 字符串处理 对于Joiner，常用的方法是 跳过NULL元素：skipNulls() / 对于NULL元素使用其他替代useForNull(String)
     * 对于Splitter，常用的方法是： trimResults()/omitEmptyStrings()。注意拆分的方式，有字符串，还有正则，还有固定长度分割
     */
    /**
     * 连接器
     */
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    /**
     * 分割器
     */
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();


    public static void main(String[] args) {
        testMultSet();
        testJonin();
    }

    private static void  testJonin(){
        String string = "111, ,222,,33";
        String join = joiner.join(Lists.newArrayList("a",null,"b"));
        System.out.println(join);

        for (String s:splitter.split(string)) {
            System.out.println(s);
        }
    }

    private static void testMultSet(){
        Multiset multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(2);
        multiset.add(3);
        multiset.add(3);
        //计算元素个数
        System.out.println(multiset.count(3));


        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("a","1");
        multimap.put("a","2");

        System.out.println(multimap.get("a"));
    }
}
