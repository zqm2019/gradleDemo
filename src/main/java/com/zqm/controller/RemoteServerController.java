package com.zqm.controller;

import com.jcraft.jsch.*;
import lombok.Data;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

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
//    public static void main(String[] args) {
//
//        String ip = "157.0.19.2";
//        String username = "root";
//        String password = "ictnj@123456";
//        DataBase dataBase = new DataBase();
//        //  连接数据类
//        dataBase.setUserName(username);
//        dataBase.setPassWord(password);
//        dataBase.setUrl(ip);
//        dataBase.setPort("10232");
//        RemoteServerController linux = new RemoteServerController();
//        System.out.println(new Date());
//        System.out.println(linux.getData(dataBase, "ps -ef |grep java"));
//        System.out.println(new Date());
//
//
//    }

    @Data
    public static class DataBase {
        private String userName;

        private String passWord;

        private String url;
        private String port;

    }

    public static void main(String[] args) throws IOException {
        while (true) {
            listFolderStructure("root", "ictnj@123456", "157.0.19.2", 10232, 5, "ls");
            System.out.println(new Date().toString());
        }

    }

    public static void listFolderStructure(String username, String password,

                                           String host, int port, long defaultTimeoutSeconds, String command) throws IOException {

        SshClient client = SshClient.setUpDefaultClient();

        client.start();

        try (ClientSession session = client.connect(username, host, port)

                .verify(defaultTimeoutSeconds, TimeUnit.SECONDS).getSession()) {

            session.addPasswordIdentity(password);

            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);

            try (ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

                 ClientChannel channel = session.createExecChannel(command)) {

                channel.setOut(responseStream);

                try {
                    channel.open().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);

                    try (OutputStream pipedIn = channel.getInvertedIn()) {

                        pipedIn.write(command.getBytes());

                        pipedIn.flush();

                    }

                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED),

                            TimeUnit.SECONDS.toMillis(defaultTimeoutSeconds));

                    String responseString = new String(responseStream.toByteArray());

                    System.out.println(responseString);

                } finally {

                    channel.close(false);

                }

            }

        } finally {

            client.stop();
        }
    }



}
