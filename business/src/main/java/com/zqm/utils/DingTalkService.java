package com.zqm.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @describe:
 * @author:zqm
 */

@Service
@Slf4j
public class DingTalkService {
    private static final String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=accessToken";

    public static void sendDingTalkMessage(String message, String token) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(WEBHOOK_TOKEN.replace("accessToken", token));
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            JSONObject o1 = new JSONObject();
            o1.put("msgtype", "markdown");
            JSONObject o2 = new JSONObject();
            o2.put("title", message);
            o2.put("text", message);
            o1.put("markdown", o2.toString());
            StringEntity se = new StringEntity(o1.toString(), "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info(result);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
//    https://oapi.dingtalk.com/robot/send?access_token=5bc63d2ee0c249ab4d6f8d5c375267125309d64cbaa8c6c814f2a9cf3c57ef63
    public static void main(String[] args) throws InterruptedException {
        for (int i=0 ; i<5;i++){
            sendDingTalkMessage("进爷牛逼,别忘了我的全家桶@陈进Jim","5bc63d2ee0c249ab4d6f8d5c375267125309d64cbaa8c6c814f2a9cf3c57ef63");
                TimeUnit.SECONDS.sleep(20);
        }
    }

    //    https://oapi.dingtalk.com/robot/send?access_token=8d4068360ee666a040631f1fc7a9e576ee3c9f5946c3e9517470e825378f97e5
//    public static void main(String[] args) {
//        List<String> list = Lists.newArrayList("你好呀", "我是牛牛派来的", "和你聊聊天", "你喝了几杯水啦");
//        list.forEach(o -> {
//            sendDingTalkMessage(String.format("艳艳，%s", o), "8d4068360ee666a040631f1fc7a9e576ee3c9f5946c3e9517470e825378f97e5");
//            try {
//                TimeUnit.SECONDS.sleep(12);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        });
//    }
}
