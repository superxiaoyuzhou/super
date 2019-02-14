package com.example.demo.AES;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *AES加密解密工具类
 *@author M-Y
 */
public class AESMD5Util {
    private static final Logger logger = Logger.getLogger(AESMD5Util.class);
    private static final String defaultCharset = "UTF-8";
    private static final String KEY_MD5 = "MD5";
    private static MessageDigest md5Digest;
    private static final String KEY = "123456";

    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            //
        }
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doMD5(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doMD5(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data 待处理数据
     * @param key  密钥
     * @param mode 加解密mode
     * @return
     */
    /**
     * 加解密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */
    private static String doMD5(String data, String key, int mode) {
        try {
            if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
                return null;
            }
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = parseHexStr2Byte(data);
            }
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(defaultCharset)), KEY_MD5);//构造一个密钥
            Cipher cipher = Cipher.getInstance(KEY_MD5);// 创建密码器
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);//加密或解密
            if (encrypt) {
                return parseByte2HexStr(result);
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            logger.error("AES 密文处理异常", e);
        }
        return null;
    }
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
        String encrypt = encrypt(content, KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = decrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }
}
