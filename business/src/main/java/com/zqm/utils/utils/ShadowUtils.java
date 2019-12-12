
package com.zqm.utils.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

import org.apache.commons.codec.binary.Base64;

/**
 * description:加解密
 * Date: 2018-12-24
 *
 * @author zqm
 */
public class ShadowUtils {

    private static final String VIPARA = "e8d8c65a1850ab48";// 128 bit
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEYVALUE = "10f9c99bfbc68e7d"; // 128 bit

    private ShadowUtils() {
    }

    /**
     * 加密，返回hex字符串
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt2Hex(String data) throws Exception {
        Key key = null;
        byte[] encVal = null;
        try {
            key = generateKey();
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            encVal = c.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new Exception("加密" + data + "失败");
        }
        return Base64.encodeBase64String(encVal);
    }

    /**
     * 解密
     *
     * @param encryptedDataHex
     * @return
     * @throws Exception
     */
    public static String decryptFromHex(String encryptedDataHex) throws Exception {
        Key key = null;
        String decryptedValue = null;
        try {
            key = generateKey();
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decodeValue = Base64.decodeBase64(encryptedDataHex.replaceAll(" ", "+"));
            byte[] decValue = c.doFinal(decodeValue);
            decryptedValue = new String(decValue, "UTF-8");
        } catch (Exception e) {
            throw new Exception("解密" + encryptedDataHex + "失败");
        }
        return decryptedValue;
    }

    /**
     * 生成加解密的key
     *
     * @return
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        SecretKeySpec key = new SecretKeySpec(KEYVALUE.getBytes(), "AES");
        return key;
    }
}
