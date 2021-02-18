package com.zqm.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("download")
@Slf4j
public class DownLoadController {

    @RequestMapping("test")
    public void test(HttpServletResponse response) {
        try {
            String nowTimeString = "ll";
            //文件的名称
            String downloadFilename = nowTimeString + ".rar";
            //转换中文否则可能会产生乱码
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            // 指明response的返回对象是文件流
            response.setContentType("application/octet-stream");
            // 设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
//            服务端向客户端游览器发送文件时，如果是浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示，如果需要提示用户保存，就要利用Content-Disposition进行一下处理，关键在于一定要加上attachment：
//            response.setHeader("Content-Disposition","attachment;filename=FileName.txt");
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            ArrayList<String> files = Lists.newArrayList(
                     "http://image.carzone.cn/commodity_qrcode/qrcode_test/1152806.jpg",
                    "http://image.carzone.cn/commodity_qrcode/qrcode_test/1152805.jpg"
                   );

            for (int i = 0; i < files.size(); i++) {
                URL url = new URL(files.get(i));
                //目录不能重复，否则会duplicate entry: ll/
                //因此需要加个时间戳或者是uuid作为后缀
                //也可以根据文件地址作为目录，会自动根据路径分区文件夹，一个/一个文件目录
                zos.putNextEntry(new ZipEntry("ll/" + i +"/"+ ".jpg"));
//              此时会自动分级目录，
//                zos.putNextEntry(new ZipEntry(files.get(i));


                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                //关闭输入流
                fis.close();
            }

            //强制把数据输出，清空缓存区，最后再关闭读写流调用close()就完成了
            zos.flush();
            //关闭输出流
            zos.close();
        } catch (Exception e) {
            log.error("下载失败", e);

        }
    }


    @RequestMapping("singleQrCode")
    public void getSingleQrCode(HttpServletResponse response, String imgUrl) {
        try {
            String downloadFilename = "new url";
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
            response.setHeader("FileName", downloadFilename);
            OutputStream outputStream = response.getOutputStream();
            URL url = new URL(imgUrl);
            InputStream fis = url.openConnection().getInputStream();
            byte[] buffer = new byte[1024];
            int r;
            while ((r = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, r);
            }
            fis.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.warn("品牌商二维码下载失败{}", JSON.toJSONString(imgUrl), e);
        }
    }

}
