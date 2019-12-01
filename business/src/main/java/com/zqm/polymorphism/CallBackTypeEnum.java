
package com.zqm.polymorphism;

/**
 * 第三方回调类型枚举
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
public enum CallBackTypeEnum {
    CALL_BACK_TYPE_ONE(1, "回调类型1"),
    CALL_BACK_TYPE_TWO(2, "回调类型2"),
    CALL_BACK_TYPE_THREE(3, "回调类型3"),
    CALL_BACK_TYPE_FOUR(4, "回调类型4");
    //todo 其他回调类型依次添加


    private int type;

    private String typeDesc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    CallBackTypeEnum() {
    }


    CallBackTypeEnum(int type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }
}



