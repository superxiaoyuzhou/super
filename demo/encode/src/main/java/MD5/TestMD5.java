package com.example.demo.MD5;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestMD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String data = "看了也没什么用！";
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //更新摘要，获得密文
        byte[] dataEncode = md5.digest(data.getBytes("utf-8"));
        String dataStr = Base64.getEncoder().encodeToString(dataEncode);
        System.out.println(dataStr);
        byte[] bytes = DigestUtils.md5Digest(data.getBytes("utf-8"));
        String dataStr2 = Base64.getEncoder().encodeToString(bytes);
        System.out.println(dataStr2);
        String dataStr3 = DigestUtils.md5DigestAsHex(data.getBytes("utf-8"));
        System.out.println(dataStr3);
    }
}
