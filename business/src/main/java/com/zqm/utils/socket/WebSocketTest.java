package com.zqm.utils.socket;

/**
 * @describe: sid 需要提前约定
 * @author:zqm
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketTest {

    private static final ConcurrentHashMap<String, Session> SESSION_MAP = new ConcurrentHashMap();

    /**
     * 接受消息
     * @param sid
     * @param session
     * @throws IOException
     * @throws InterruptedException

    //    @OnMessage
    //    public void onMessage(@PathVariable("sid") String sid, Session session)
    //            throws IOException, InterruptedException {
    //        System.out.println();
    //    }
     */

    /**
     * 接受消息
     *
     * @param message 客户端发送的消息
     * @param session
     * @throws IOException
     * @throws InterruptedException
     */

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(session.getPathParameters().get("sid"));
        System.out.println(message);
    }

    /**
     * 登记连接
     *
     * @param sid
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("sid") String sid, Session session) {
        if (SESSION_MAP.containsKey(sid)) {
            return;
        }
        SESSION_MAP.put(sid, session);
        System.out.println("Client connected");
    }


    /**
     * 注销连接
     */
    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }


    public void sendMessage(String sid, String message) throws IOException {
        if (StringUtils.isNotBlank(sid)) {
            Session session = SESSION_MAP.get(sid);
            session.getBasicRemote().sendText(message);
        }

    }
}
