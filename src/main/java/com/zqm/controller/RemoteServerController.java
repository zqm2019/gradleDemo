package com.zqm.controller;

import com.jcraft.jsch.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("remote")
public class RemoteServerController {
    static Session  session = null;

    public Boolean connectTest(DataBase dataBase) {
        //连接检测

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(dataBase.getUserName(), dataBase.getUrl(), Integer.parseInt(dataBase.getPort()));
            session.setPassword(dataBase.getPassWord());
//            // 设置第一次登陆的时候提示，可选值:(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            //设置密码登录 不使用密钥登录
            session.setConfig("PreferredAuthentications", "password");

            // 连接超时
            session.connect(1000 * 10);
            if (session.isConnected()) {
                session.disconnect();
            }

            return true;
        } catch (JSchException e) {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
            return false;
        }

    }

    public String getData(DataBase dataBase, String command) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(dataBase.getUserName(), dataBase.getUrl(), Integer.parseInt(dataBase.getPort()));
            session.setPassword(dataBase.getPassWord());
            // 设置第一次登陆的时候提示，可选值:(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("PreferredAuthentications","password");
            // 连接超时
            session.connect(10000 * 10);
            byte[] tmp = new byte[1024];
            // 命令返回的结果
            StringBuffer resultBuffer = new StringBuffer();
            Channel channel = session.openChannel("exec");
            ChannelExec exec = (ChannelExec) channel;
            // 返回结果流（命令执行错误的信息通过getErrStream获取）
            InputStream errorStream = exec.getErrStream();
            exec.setCommand(command);
            exec.connect();
            String result = "";
            try {
                // 开始获得SSH命令的结果
                while (true) {
                    InputStream inputStream = exec.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String buf;
                    while ((buf = reader.readLine()) != null) {
                        resultBuffer.append(new String(buf.getBytes("gbk"), "UTF-8") + " \r\n");
                    }
                    while (errorStream.available() > 0) {
                        int i = errorStream.read(tmp, 0, 1024);
                        if (i < 0) {
                            break;
                        }
                        resultBuffer.append(new String(tmp, 0, i));
                    }
                    if (exec.isClosed()) {
                        break;
                    }
                }
            } finally {
                //关闭连接
                channel.disconnect();
            }


            return resultBuffer.toString();
        } catch (Exception e) {
            return "";

        } finally {
           close();
        }

    }


    /**
     * 关闭连接
     */
    private static void close() {
        if (session != null && session.isConnected()){
            session.disconnect();
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {

        String ip = "157.0.19.2";
        String username = "root";
        String password = "ictnj@123456";
        DataBase dataBase = new DataBase();
        //  连接数据类
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        dataBase.setUrl(ip);
        dataBase.setPort("10232");
        RemoteServerController linux = new RemoteServerController();
        System.out.println(new Date());
        System.out.println(linux.getData(dataBase, "ps -ef |grep java"));
        System.out.println(new Date());


    }

    @Data
    public static class DataBase {
        private String userName;

        private String passWord;

        private String url;
        private String port;

    }

}
