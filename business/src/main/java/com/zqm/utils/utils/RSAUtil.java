package com.zqm.utils.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象（KeyPairGenerator，密钥对生成器，用于生成公钥和私钥对）
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded()); //返回一个publicKey经过二次加密后的字符串
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded()); //返回一个privateKey经过二次加密后的字符串

        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }



        public static void main (String[] args) throws Exception {
            Map<String, String> keyMap = RSAUtil.createKeys(1024);
            String  publicKey = keyMap.get("publicKey");
            String  privateKey = keyMap.get("privateKey");
            System.out.println("公钥: \n\r" + publicKey);
            System.out.println("私钥： \n\r" + privateKey);

            System.out.println("公钥加密——私钥解密");
            String str = "寒山月初\n" +
                    "dfadsfasdfasdf";
            System.out.println("\r明文：\r\n" + str);
            System.out.println("\r明文大小：\r\n" + str.getBytes().length);
            String encodedData = RSAUtil.publicEncrypt(str, RSAUtil.getPublicKey(publicKey));
            System.out.println("密文：\r\n" + encodedData);
            String decodedData = RSAUtil.privateDecrypt(encodedData, RSAUtil.getPrivateKey(privateKey));
            System.out.println("解密后文字: \r\n" + decodedData);
        }

//    公钥:
//    MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBQTUP-64NiiwoI5ycPXLLZJiHGXoI3oD3wVdZyIGXch1xx1SAlvQuWXxkx4UrTx2P3B4owNGNk1ZNjdi3GnT8k9kVAeRQzPrut6xpYaRvedo33Iy914n1I04Sv95n8f-OUdmj5QaCxQ697J9mVcEPCX6HkeXp14h8YxoCk6GduQIDAQAB
//    私钥：
//    MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMFBNQ_7rg2KLCgjnJw9cstkmIcZegjegPfBV1nIgZdyHXHHVICW9C5ZfGTHhStPHY_cHijA0Y2TVk2N2LcadPyT2RUB5FDM-u63rGlhpG952jfcjL3XifUjThK_3mfx_45R2aPlBoLFDr3sn2ZVwQ8JfoeR5enXiHxjGgKToZ25AgMBAAECgYAWpAsRak0D7RIrSbDJZTGTvp9YBJYVf-vgGtie_iygj4__injN7-F_pKA8NlvVrRkrh4BJCf4C9iYARAQFv6XA1VyDC1l7VUfJnN9YDQShvW8O0Kqlk_0MTRmYvGWDPxRiaHcjgdoOd_4l3hX05LdASOA4sn73gkqilzz0iVV0AQJBAO4UbhmkAQuOKH5MjvZCEdJRP4TYUV7ZKq-7kRKrl2U4932Xb0MJ-2gKeWgkpgEzVx64r9E-UTF-N9zjyvwhO0ECQQDPzQpniJMDq-TxhhlbbIsQZwUFkWgMkyxnxcrDMiji8Z4RwaPDE4YpUORrAxZlnrCCy6iVQStg0TkxNGyYkZx5AkAMHYAIz_65ns9StgSkZXJQuI-56-QgJn70mF_milLW9NIrUvR3fd-MSNQqx4qnDvzE9HMF-9zX3utq0zC2Kd7BAkEAqMnCpsA_sh7A0xH6rNEo0ATuBkzKuFQfIJd_kDfBED30CDZCf6TvN8YWE3opihdxBnzIjZum0XiAvR_5CaBU-QJBALKCVwQ0JmQY_ZI0n6dsQiB7pvUkNNq4r-GOBO2n-Leqh3gKweI5f_Y3sb0Mna3PPV1SuL4Y1T_GmBZJ5XqzMQA
//    公钥加密——私钥解密
//    明文：
//    寒山月初dfadsfasdfasdf
//    明文大小：
//            26
//    密文：
//    u-hH9mMjGCF6wzgl2uawp7L7YGeStL2rNBh7rvqAIXVbHt6grLfBi-owHJe88Te1BzhuzfqWXpZb0hazQ8Ng77shKb-wa4mDoAqYWtMZi0X_sCN_UoqEscAuQhEFzgE04zySaiJWC25dlK_90wk-bhlm50n0T0mEC2PdYhVOrqs
//    解密后文字:
//    寒山月初dfadsfasdfasdf



}
