package com.zqm.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @describe:
 * @author:zqm
 */
public class FileUtils {

    public static String download(HttpServletResponse response, String fileName, String tel) {
        String filePath = "/Users/zhaqianming/Documents/project/";

        // 设置文件存储路径
        if (!StringUtils.isBlank(tel)) {
            filePath = filePath + tel + "/";
        }
        String path = filePath + fileName;
        File file = new File(path);

        if (file.exists()) {
            BufferedInputStream bufferedInputStream = null;
            BufferedOutputStream bufferedOutputStream = null;
            try {

                // 清除buffer缓存
                response.reset();
                // 指定下载的文件名
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + fileName + "");
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");


                FileInputStream inputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(inputStream); //缓冲流加速读
                OutputStream outputStream = response.getOutputStream();
                bufferedOutputStream = new BufferedOutputStream(outputStream);  //缓冲流加速写
                int n;
                while ((n = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new RuntimeException("文件在本地服务器不存在");
        }
        return "sss";
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String downloadFiles(HttpServletRequest request, HttpServletResponse response, String fileName) {

        if (org.springframework.util.StringUtils.isEmpty(fileName)) {
            return "文件名称为空";
        }

        //设置文件路径
        ClassPathResource classPathResource = new ClassPathResource("/Users/zhaqianming/Documents/project/" + fileName);
        File file = null;
        try {
            file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            return "文件不存在";
        }

        response.setHeader("content-type", "application/octet-stream");
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        byte[] buffer = new byte[1024];
        InputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件下载成功";
    }

    /**
     * 判断文件大小
     *
     * @param file 文件
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        if (file.isEmpty() || org.springframework.util.StringUtils.isEmpty(size) || org.springframework.util.StringUtils.isEmpty(unit)) {
            return false;
        }
        long len = file.getSize();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    /**
     * 分割文件
     */

    /**
     * @param bis 输入流
     * @param filePath       新文件存储的文件夹路径
     * @param partitionSize 每个文件的大小 单位MB
     * @throws IOException
     */
    private static boolean singleSplitFile(BufferedInputStream bis, String filePath,int partitionSize) throws IOException {
        int length = partitionSize * 1024 * 1024;
        byte[] buffer = new byte[1024];
        int tempLength;

        System.out.println("----->>>> begin split " + filePath);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            while ((tempLength = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, tempLength);
                if ((length = length - tempLength) <= 0) {
                    break;
                }
            }
        }
        System.out.println("<<<<----- yet finish split" + filePath);

        return tempLength < 0;
    }

    /**
     * 把大文件拆分为多个小文件
     *
     * @param sourcePath 需要被拆分的文件路径
     * @param desPath    拆分后的文件应该存放的文件路径
     * @param newName    每一个小文件名
     */
    public static void splitFile(String sourcePath, String desPath, String newName,String fileType) {

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(sourcePath)))) {
            for (int i = 0; ; i++) {
                String filePath = desPath + File.separatorChar +newName+"_" +i+"."+fileType;
                if (singleSplitFile(bis, filePath, 10)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        splitFile("/Users/zhaqianming/Documents/书籍/深入JAVA虚拟机第二版.pdf","/Users/zhaqianming/Documents/南研院","testbook","pdf");
    }
}
