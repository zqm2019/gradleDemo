
package com.zqm.controller;

import com.zqm.vo.People;

/**
 * java只有值传递
 * 基础类型不会改变原始变量的值
 * 引用数据类型能改变原始变量的值
 * 当传递方法参数类型为引用数据类型时，一个方法将修改一个引用数据类型的参数所指向对象的值，仍然是指调用
 * Date: 2019-09-04
 *
 * @author zhaqianming
 */
public class CallByValue {

    private static int a;

    public static void main(String[] args) {
        //基础数据类型
        System.out.println(a);
        method1(a);
        System.out.println(a);

        method2();
        System.out.println(a);

        //引用数据类型
        user = new People();
        System.out.println("调用前user的值：" + user.toString());
        updateUser(user);
        System.out.println("调用后user的值：" + user.toString());

    }

    //值引用
    public static void method1(int a) {
        a = 22;
    }


    public static void method2() {
        a = 4;
    }


    private static People user = null;

    public static void updateUser(People student) {
        student.setName("Lishen");
        student.setAge(18);
    }

}
