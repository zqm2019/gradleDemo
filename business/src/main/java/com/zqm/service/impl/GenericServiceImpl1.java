package com.zqm.service.impl;

import org.springframework.stereotype.Service;

import com.zqm.service.GenericService;

/**
 * TODO: description
 * Date: 2019-12-06
 *
 * @author zhaqianming
 */
@Service
public class GenericServiceImpl1<T> implements GenericService<T> {

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
