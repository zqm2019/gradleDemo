
package com.zqm.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 * Date: 2019-07-18
 *
 * @author zhaqianming
 */
@RestController
public class TestController {
    public static class Data{
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    @RequestMapping("/jj")
    public String hello(@RequestBody Data data ) {
        return "Hello World!";
    }
}
