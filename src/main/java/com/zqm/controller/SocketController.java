/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java  网络通信通过socket实现
 * Socket分为ServiceSocket(用于服务端,通过accept()监听请求)，Socket用于完成数据传输
 * <p>
 * NioSocket(new io socket) 新io
 *
 * ServerSocketChannel 和 SocketChannel 对应ServiceSocket，Socket
 * Date: 2019-12-01
 *
 * @author zqm
 */
public class SocketController {

    /**
     * 先启用Service端，再启用Client端，完成一次通信。
     */
    public static class Service {
        public static void main(String[] args) {
            try {
                //创建一个ServerSocket监听端口号8080
                ServerSocket serviceSocket = new ServerSocket(8080);
                //等待请求
                Socket socket = serviceSocket.accept();
                //接受到请求后使用socket通信。创建BufferedReader用于读取数据
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //读数据
                String line = bufferedReader.readLine();
                System.out.println("receive from client:" + line);
                //创建PrintWriter发送数据
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("receive data:" + line);
                printWriter.flush();
                //倒序关闭
                printWriter.close();
                bufferedReader.close();
                socket.close();
                serviceSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static class Client {
        public static void main(String[] args) {

            String msg = "Client data";
            try {

                //创建一个Socket，跟本机的8080端口连接
                Socket socket = new Socket("127.0.0.1", 8080);
                //使用Socket创建PrintWriter和BufferedReader进行读写数据
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //发送数据
                printWriter.println(msg);
                printWriter.flush();
                //接受数据
                String line = bufferedReader.readLine();
                System.out.println("receive from server" + line);
                //关闭资源
                printWriter.close();
                bufferedReader.close();
                socket.close() ;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
