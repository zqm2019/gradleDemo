package com.zqm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @describe:
 * @author:zqm
 */
public class DaemonController implements Runnable {


    @Override
    public void run() {

        try {
            //守护线程阻塞1秒后运行
            Thread.sleep(1000);

            File f = new File("daemon.txt");

            FileOutputStream os = null;
            try {
                os = new FileOutputStream(f, true);
                os.write("daemon".getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Runnable tr = new DaemonController();

        Thread thread = new Thread(tr);

        //设置守护线程
//        thread.setDaemon(true);
//
        //开始执行分进程
        thread.start();

    }

}
