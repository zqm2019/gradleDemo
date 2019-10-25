
package com.zqm.polymorphism;

/**
 * 第三方回调类型枚举
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
public enum CallBackTypeEnum {
    CREATE_ROOM(4001, "创建群回调"),
    ROOM_QR_CODE(4008, "获取二维码");


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
    }}



