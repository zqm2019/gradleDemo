package com.zqm.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @describe:
 * @author:zqm
 */
public class JFram {

    private JFrame jFrame = new JFrame("license生成器");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("输入JSON串格式:");
    private JTextField username = new JTextField();
    private JLabel a2 = new JLabel("license结果:");
    private JTextField password = new JTextField();
    private JButton okbtn = new JButton("确定生成");
//    private JButton cancelbtn = new JButton("取消");

    public JFram() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 300, 800, 600);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
        listerner();
    }

    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("license生成器"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 200, 100);
        a2.setBounds(50, 200, 300, 100);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(200, 20, 300, 100);
        password.setBounds(200, 200, 300, 100);
        fieldPanel.add(username);
        fieldPanel.add(password);
        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
//        buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");
    }

    //测试
    public static void main(String[] args) {
        new JFram();

    }

    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = username.getText();
                String pwd = String.valueOf(password.getText());
                password.setText("aaa");
                System.out.println(uname+pwd);
            }
        });
        //取消按下去清空
//        cancelbtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                password.setText("aaa");
//            }
//        });
    }
}