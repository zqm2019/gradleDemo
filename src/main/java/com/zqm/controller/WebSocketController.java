package com.zqm.controller;

import com.zqm.utils.socket.WebSocketTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("web-socket")
public class WebSocketController {

    @Autowired
    private WebSocketTest webSocketTest;

    @RequestMapping("send")
    public void sendMessage(String message) throws IOException {
        webSocketTest.sendMessage("user000",message);
    }
}
