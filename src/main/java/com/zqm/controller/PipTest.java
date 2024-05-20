package com.zqm.controller;

import java.io.*;

/**
 * @describe:
 * @author:zqm
 */
public class PipTest {


    public static class PipedIn extends Thread {
        private PipedInputStream inputStream = new PipedInputStream();

        public PipedInputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(PipedInputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            try {
                //这是管道读入流从管道写入流中读出数据
                int len = inputStream.read(buffer);
                InputStreamReader reader= new InputStreamReader(inputStream);
                //reader.re
                System.out.println(new String(buffer, 0, len));
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static class PipedOut extends Thread {
        private PipedOutputStream outputStream = new PipedOutputStream();

        public PipedOutputStream getOutputStream() {
            return outputStream;
        }

        public void setOutputStream(PipedOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public void run() {
            try {
                //这是往管道流中写入数据
                outputStream.write("这是一个坑爹的年代".getBytes());
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) throws IOException {
        /*
         * 这个例子主要是建立两个线程，然后在一个线程中建立一个管道写入流，在另外一个线程中建立一个管道读出流
         * 然后进行线程之间的相互通信
         */
        PipedIn in = new PipedIn();
        PipedOut out = new PipedOut();
        //这是用管道读出流连接管道写入流，也可以用管道写入流连接管道读出流
        in.getInputStream().connect(out.getOutputStream());
        in.start();
        out.start();
    }

}