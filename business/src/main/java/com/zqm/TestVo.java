package com.zqm;

import com.alibaba.fastjson.JSON;
import com.zqm.vo.People;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe:
 * @author:zqm
 */
public class TestVo {


    public class GatewayResultRo<T> implements Serializable {

        private static final long serialVersionUID = 2320404791173045097L;
        /**
         * 状态码
         */
        private String code;

        /**
         * 消息提示
         */
        private String message;

        /**
         * 结果
         */
        private transient T  data;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {



        int a =1;
        int b= 2;
        int c =3;

        a=b;
        System.out.println(a);
        a=c;
        System.out.println(a);









        Map<String, People> map = new ConcurrentHashMap<>();
        People people = new People();
        people.setSex("1");
        map.put("1", people);
        People people3 = map.get("1");
        System.out.println("第1组:");
        System.out.println(JSON.toJSONString(people3));
        people3.setName("third");
        //people3的引用和map.get("1")是一个
        System.out.println(JSON.toJSONString(map.get("1")));
        System.out.println(people3.equals(map.get("1")));

        People people1 = new People();
        People people2 = new People();
        people2.setName("second");

        people1 = people2;
        System.out.println("第二组:");
        System.out.println(JSON.toJSONString(people1));
        System.out.println(JSON.toJSONString(people2));
        System.out.println(people1.equals(people2));

        people1.setName("first");
        //此时name都是first。
        System.out.println("第三组:");
        System.out.println(JSON.toJSONString(people1));
        System.out.println(JSON.toJSONString(people2));
        System.out.println(people1.equals(people2));


        people1 = People.builder().name("fourth").build();
        //此时people1的引用指向的是另一个对象
        System.out.println("第四组:");
        System.out.println(JSON.toJSONString(people1));
        System.out.println(JSON.toJSONString(people2));
        System.out.println(people1.equals(people2));


    }
}
