/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: description
 * Date: 2019-11-27
 *
 * @author zhaqianming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryRobotInfoByRoomIdEntity {

    /**
     * 商家编号
     */
    private String merchantNumber;

    /**
     * 机器人编号
     */
    private String serialNumber;

    /**
     * 供应商 1涂色2橘子
     */
    private int supplier;

    /**
     * 商家密钥
     */
    private String merchantSecret;

    /**
     * 群编号
     */
    private String roomSerialNumber;

}
