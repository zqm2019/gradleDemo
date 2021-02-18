package com.zqm.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @describe:
 * @author:zqm
 */
public class IoController {


    public static void main(String[] args) {
        System.out.println(readContentByInputStream());
    }


    /**
     * 读取输入流中的内容 输入流转string
     */
    public static String readContentByInputStream() {
        InputStream inputStream = new ByteArrayInputStream("水电费第三方sad".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i = -1;
        try {
            while ((i = inputStream.read()) != -1) {
                bos.write(i);
            }
        } catch (Exception e) {

        }

        return bos.toString();
    }

    /**
     * string 转输入流
     */

    public static InputStream gerInputStreamByString(String str) {
        InputStream inputStream = new ByteArrayInputStream("水电费第三方sad".getBytes());
        return inputStream;
    }

}
