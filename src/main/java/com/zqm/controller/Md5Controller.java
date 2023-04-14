package com.zqm.controller;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @describe:
 * @author:zqm
 */
public class Md5Controller {
    public static String encryptToMD5(String str) {
        return DigestUtils.md2Hex(str);

    }

    public static void main(String[] args) {
        System.out.println(encryptToMD5("a"));
        System.out.println(decryptToString(encryptToMD5("a")));
    }

    public static Object decryptToString(String str){
        return DigestUtils.getDigest(str);
    }


}
