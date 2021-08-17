package com.zqm.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RequestMapping("del")
@RestController
public class DeleteController {

    @DeleteMapping("/{id}/{name}")
    public void testDelete(@PathVariable int id,@PathVariable String name){
        System.out.println(id);
        System.out.println(name);
        System.out.println(1);

    }

    @RequestMapping("ll")
    public String ll(){
        return "ok";
    }
}
