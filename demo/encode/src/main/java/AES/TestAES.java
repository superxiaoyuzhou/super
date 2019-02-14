package com.example.demo.AES;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TestAES {
    /*
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String ecnodeRules, String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        //1.构造密钥生成器，指定为AES算法
        KeyGenerator aesKey = KeyGenerator.getInstance("AES");
        //2.根据规则（ecnodeRules）初始化密钥生成器,指定为128位
        aesKey.init(128, new SecureRandom(ecnodeRules.getBytes()));
        //3.根据密钥生成器产生原始对称密钥
        SecretKey secretKey = aesKey.generateKey();
        //4.根据原始对称密钥生成AES密钥
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //5.根据指定(AES)算法,生成密码器
        Cipher cipher = Cipher.getInstance("AES");
        //6.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //7.通过密码器对加密内容进行加密,转换为字节数组（设置为utf-8，防止中英文乱码），
        byte[] byteAES = cipher.doFinal(content.getBytes("utf-8"));
        //8.将加密后的字节数组转换为字符串,返回
        //return new String(byteAES, "utf-8");
        return new String(new BASE64Encoder().encode(byteAES));
    }

    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDncode(String encodeRules, String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        //1.构造密钥生成器，指定为AES算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //2.根据规则（ecnodeRules）初始化密钥生成器,指定为128位
        keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
        //3.根据密钥生成器产生原始对称密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = secretKey.getEncoded();
        //5.把密钥转换为AES密钥
        SecretKeySpec key = new SecretKeySpec(raw, "AES");
        //5.根据指定(AES)算法,生成密码器
        Cipher cipher = Cipher.getInstance("AES");
        //6.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, key);
        //7.通过密码器对加密内容进行解密,转换为字节数组（设置为utf-8，防止中英文乱码），使用加密解密时，输入长度必须是16的倍数
        byte[] byteAES = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));         //解密
        //8.返回解密
        return new String(byteAES, "utf-8");
    }

    public static void main(String[] args) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException {
        String content ="今天天气好啊!!!";
        System.out.println("加密内容为：" + content);
        String encode ="password123";
        System.out.println("加密规则为：" + encode );
        String aesEncode = AESEncode(encode, content);
        System.out.println("加密后为：" +  aesEncode);

        String dncode = AESDncode(encode, aesEncode);
        System.out.println("解密后为：" +dncode );
    }
}
