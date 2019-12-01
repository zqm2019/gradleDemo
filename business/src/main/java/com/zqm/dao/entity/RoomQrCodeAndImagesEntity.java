/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.dao.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信二维码&群名称&群成员头像(最多十个)
 * Date: 2019-11-26
 *
 * @author zhaqianming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomQrCodeAndImagesEntity {

    private String roomId;

    private String roomName;

    private List<String> imageList;

    private int roomNumber;

}
