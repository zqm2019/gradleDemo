
package com.zqm.service;

/**
 * 泛型接口
 * Date: 2019-12-06
 *
 * @author zhaqianming
 */
public interface GenericService<T> {
    int getType();
    void show(T t);
}
