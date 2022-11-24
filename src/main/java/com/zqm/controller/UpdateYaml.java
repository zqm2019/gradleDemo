//package com.zqm.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.context.refresh.ContextRefresher;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.MapPropertySource;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//
//@RestController
//@RefreshScope
//public class UpdateYaml {
//
//    @Autowired
//    private ContextRefresher contextRefresher;
//
//    @Autowired
//    private ConfigurableEnvironment environment;
//
//    @Value("${test}")
//    private String test;
//
//
//    @GetMapping("test1")
//    public String getInxo1() {
//
//        return this.test;
//    }
//
//
//    @PostMapping("refresh")
//    public String default02(@RequestParam String name){
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("test",name);
//        MapPropertySource propertySource = new MapPropertySource("dynamic",map);
//        environment.getPropertySources().addFirst(propertySource);
//        new Thread(() -> contextRefresher.refresh()).start();
//        return "success";
//    }
//
//}