
package com.zqm.vo;


import lombok.Data;

/**
 * 出参统一规范
 * Date: 2019-07-22
 *
 * @author zhaqianming
 */
@Data
public abstract class BaseResponse<T> {
    private boolean success = true;

    private int errorCode = 10001;

    private String msg = "ok";

    public abstract T getData();

    public abstract void setData(T data);

}
