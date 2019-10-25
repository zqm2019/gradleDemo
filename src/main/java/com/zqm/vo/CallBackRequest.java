
package com.zqm.vo;

/**
 * Date: 2019-10-08
 *
 * @author zhaqianming
 */
public class CallBackRequest {

    //回调类型
    private int nType;

    //回调入参
    private String strContext;

    public int getnType() {
        return nType;
    }

    public void setnType(int nType) {
        this.nType = nType;
    }

    public String getStrContext() {
        return strContext;
    }

    public void setStrContext(String strContext) {
        this.strContext = strContext;
    }
}
