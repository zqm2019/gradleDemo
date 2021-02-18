package com.zqm.utils.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zam
 */
public final class ExcelUtils {

    private ExcelUtils() {}

    /**
     * 浏览器IO输出（兼容Mac下Safari）
     *
     * @param response
     * @param os
     * @param fileName
     * @throws IOException
     */
    public static void writeIO(HttpServletResponse response, ByteArrayOutputStream os, String fileName) throws IOException {
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes("UTF-8"), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[10240];

            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException var13) {
            throw var13;
        } finally {
            if (bis != null) {
                bis.close();
            }

            if (bos != null) {
                bos.close();
            }
        }
    }
}
