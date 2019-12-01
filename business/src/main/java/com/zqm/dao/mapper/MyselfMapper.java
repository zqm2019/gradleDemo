/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zqm.dao.entity.QueryRobotInfoByRoomIdEntity;
import com.zqm.dao.entity.RoomQrCodeAndImagesEntity;
import com.zqm.dao.entity.TNewsInfo;

/**
 * TODO: description
 * Date: 2019-11-27
 *
 * @author zhaqianming
 */
public interface MyselfMapper {



    Map<Object,Object> selectMap(@Param("id")int id);


    TNewsInfo selectNewsInfo(@Param("id") Integer id);

    /**
     * 查询wxid、robotid &群成员头像(最多十个)
     *
     * @param roomId
     * @return
     */
    RoomQrCodeAndImagesEntity selectRoomInfoAndMemberImages(@Param("roomId") String roomId);

    /**
     * 根据群id查询群头像(最多十个)
     *
     * @param roomId
     * @return
     */
    List<String> selectMemberImagesByRoomId(@Param("roomId") String roomId);

    /**
     * 根据群id查询机器人商家信息
     *
     * @param roomId
     * @return
     */
    QueryRobotInfoByRoomIdEntity selectRobotInfoByRoomId(@Param("roomId") String roomId);
}
