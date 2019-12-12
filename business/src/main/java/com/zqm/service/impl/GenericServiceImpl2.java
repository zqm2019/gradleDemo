package com.zqm.service.impl;

import org.springframework.stereotype.Service;

import com.zqm.service.GenericService;

/**
 * 指定类型实现泛型接口
 * Date: 2019-12-06
 *
 * @author zhaqianming
 */
@Service
public class GenericServiceImpl2 implements GenericService<Integer> {

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void show(Integer t) {
        System.out.println(t);
    }
}
