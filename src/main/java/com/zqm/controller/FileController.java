//package com.zqm.controller;
//
//import cn.hutool.captcha.CaptchaUtil;
//import cn.hutool.captcha.CircleCaptcha;
//import com.zqm.utils.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.List;
//
///**
// * @describe:
// * @author:zqm
// */
//
//@RestController
//public class FileController {
//    private static final Logger log = LoggerFactory.getLogger(FileController.class);
//
//    @RequestMapping(value = "/upload")
//    public String upload(@RequestParam("file") MultipartFile file, String tel) {
//        try {
//            if (file.isEmpty()) {
//                return "文件为空";
//            }
//            // 获取大小
//            long size = file.getSize();
//            log.info("文件大小： " + size);
//            // 判断上传文件大小
//            if (!FileUtils.checkFileSize(file, 50, "M")) {
//                log.error("上传文件规定小于50MB");
//                return "上传文件规定小于50MB";
//            }
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            log.info("上传的文件名为：" + fileName);
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            log.info("文件的后缀名为：" + suffixName);
//            String filePath = "/Users/zhaqianming/Documents/project/";
//
//            // 设置文件存储路径
//            if (!StringUtils.isBlank(tel)) {
//                filePath = filePath + tel + "/";
//            }
//            String path = filePath + fileName;
//            File dest = new File(path);
//            // 检测是否存在目录
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();// 新建文件夹
//            }
//            file.transferTo(dest);// 文件写入
//            return "上传成功";
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return "上传失败";
//    }
//
//    @PostMapping("/batch")
//    public String handleFileUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            String filePath = "/Users/zhaqianming/Documents/project/";
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
//                    stream.write(bytes);// 写入
//                    stream.close();
//                } catch (Exception e) {
//                    stream = null;
//                    return "第 " + i + " 个文件上传失败 ==> "
//                            + e.getMessage();
//                }
//            } else {
//                return "第 " + i
//                        + " 个文件上传失败因为文件为空";
//            }
//        }
//        return "上传成功";
//    }
//
//    @GetMapping("/download")
//    public String downloadFile(String fileName, String tel, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(500, 300);
//        circleCaptcha.write(response.getOutputStream());
//        circleCaptcha.verify("1234");
//        return "";
////        String result = FileUtils.download(response, fileName, tel);
////        if (request == null) {
////            return null;
////        }
////        return result;
//    }
//
//
//    @GetMapping("/readFile")
//    public void getReadFile(HttpServletResponse response, String fileName) {
//         fileName = "项目研发流程.pdf";
//        ServletOutputStream outputStream = null;
//        try {
//            outputStream = response.getOutputStream();
//
//            ClassPathResource resource = new ClassPathResource("guide/" + fileName);
//            InputStream inputStream = resource.getInputStream();
//
//            byte[] buf = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(buf)) != -1) {
//                outputStream.write(buf, 0, length);
//            }
//            outputStream.flush();
//        } catch (
//                IOException e) {
//            throw new Exception("获取失败！");
//        } finally {
//            if (null != outputStream) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//
//                }
//            }
//        }
//    }
//}