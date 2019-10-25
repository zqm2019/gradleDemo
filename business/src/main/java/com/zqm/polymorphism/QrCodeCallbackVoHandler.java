
package com.zqm.polymorphism;

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
public class QrCodeCallbackVoHandler extends AbstractCallBackHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(QrCodeCallbackVoHandler.class);


    @Override
    public void buildCall(String callContent, AbstractCallBackHandler abstractCallBackHandler) {
        QrCodeCallbackVoHandler qrCodeCallbackVoHandler = (QrCodeCallbackVoHandler) JSON.parseObject(callContent, abstractCallBackHandler.getClass());

        //do you want to do

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

    private Data data;

    public static class Data {

        /**
         * 群id
         */
        private String vcChatRoomId;

        /**
         * 群编号
         */
        private String vcChatRoomSerialNo;

        /**
         * 群二维码
         */
        private String vcChatRoomQRCode;


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

        public String getVcChatRoomQRCode() {
            return vcChatRoomQRCode;
        }

        public void setVcChatRoomQRCode(String vcChatRoomQRCode) {
            this.vcChatRoomQRCode = vcChatRoomQRCode;
        }
    }


    public static Logger getLOGGER() {
        return LOGGER;
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

    public String getVcRobotSerialNo() {
        return vcRobotSerialNo;
    }

    public void setVcRobotSerialNo(String vcRobotSerialNo) {
        this.vcRobotSerialNo = vcRobotSerialNo;
    }

    public String getVcSerialNo() {
        return vcSerialNo;
    }

    public void setVcSerialNo(String vcSerialNo) {
        this.vcSerialNo = vcSerialNo;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}
