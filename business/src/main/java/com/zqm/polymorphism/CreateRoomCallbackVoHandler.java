
package com.zqm.polymorphism;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


/**
 * 创建微信群回调vo
 * Date: 2019-10-22
 *
 * @author zhaqianming
 */
@Component
public class CreateRoomCallbackVoHandler extends AbstractCallBackHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(CreateRoomCallbackVoHandler.class);


    @Override
    public void buildCall(String callContent, AbstractCallBackHandler abstractCallBackHandler) {
        CreateRoomCallbackVoHandler createRoomCallbackVoHandler = (CreateRoomCallbackVoHandler) JSON.parseObject(callContent, abstractCallBackHandler.getClass());
        //do something
    }


    /**
     * 商家编号
     */
    private String vcMerchantNo;

    /**
     * 机器人wxid
     */
    private String vcRobotWxId;

    /**
     * 机器人编号
     */
    private String vcRobotSerialNo;

    /**
     * 操作编号
     */
    private String vcSerialNo;

    /**
     * 返回状态 1 成功 0 失败
     */
    private int nResult;

    /**
     * 返回信息 SUCCESS 成功 FALL 失败
     */
    private String vcResult;

    private List<Data> data;

    public static class Data {

        /**
         * 群ID
         */
        private String vcChatRoomId;

        /**
         * 群编号
         */
        private String vcChatRoomSerialNo;

        /**
         * 群人数
         */
        private int nUserCount;

        /**
         * 群编号
         */
        private String vcName;

        /**
         * 群主微信id
         */
        private String vcAdminWxId;

        /**
         * 群头像
         */
        private String vcHeadImg;

        /**
         * 创建时间
         */
        private String dtCreateTime;


        public String getVcChatRoomId() {
            return vcChatRoomId;
        }

        public void setVcChatRoomId(String vcChatRoomId) {
            this.vcChatRoomId = vcChatRoomId;
        }

        public String getVcChatRoomSerialNo() {
            return vcChatRoomSerialNo;
        }

        public void setVcChatRoomSerialNo(String vcChatRoomSerialNo) {
            this.vcChatRoomSerialNo = vcChatRoomSerialNo;
        }

        public int getnUserCount() {
            return nUserCount;
        }

        public void setnUserCount(int nUserCount) {
            this.nUserCount = nUserCount;
        }

        public String getVcName() {
            return vcName;
        }

        public void setVcName(String vcName) {
            this.vcName = vcName;
        }

        public String getVcAdminWxId() {
            return vcAdminWxId;
        }

        public void setVcAdminWxId(String vcAdminWxId) {
            this.vcAdminWxId = vcAdminWxId;
        }

        public String getVcHeadImg() {
            return vcHeadImg;
        }

        public void setVcHeadImg(String vcHeadImg) {
            this.vcHeadImg = vcHeadImg;
        }

        public String getDtCreateTime() {
            return dtCreateTime;
        }

        public void setDtCreateTime(String dtCreateTime) {
            this.dtCreateTime = dtCreateTime;
        }
    }

    public String getVcMerchantNo() {
        return vcMerchantNo;
    }

    public void setVcMerchantNo(String vcMerchantNo) {
        this.vcMerchantNo = vcMerchantNo;
    }

    public String getVcRobotWxId() {
        return vcRobotWxId;
    }

    public void setVcRobotWxId(String vcRobotWxId) {
        this.vcRobotWxId = vcRobotWxId;
    }

    public String getVcSerialNo() {
        return vcSerialNo;
    }

    public void setVcSerialNo(String vcSerialNo) {
        this.vcSerialNo = vcSerialNo;
    }


    public String getVcRobotSerialNo() {
        return vcRobotSerialNo;
    }

    public void setVcRobotSerialNo(String vcRobotSerialNo) {
        this.vcRobotSerialNo = vcRobotSerialNo;
    }

    public int getnResult() {
        return nResult;
    }

    public void setnResult(int nResult) {
        this.nResult = nResult;
    }

    public String getVcResult() {
        return vcResult;
    }

    public void setVcResult(String vcResult) {
        this.vcResult = vcResult;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
