//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.demo.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.springframework.util.DigestUtils;

public class MD5Util {
  public MD5Util() {}

  public static String md5(String data) {
    try {
      return DigestUtils.md5DigestAsHex(data.getBytes("utf-8")).toUpperCase();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String md5UpperCase(String text, String salt) throws Exception {
    byte[] bytes = (text + salt).getBytes();
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(bytes);
    bytes = messageDigest.digest();
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < bytes.length; ++i) {
      if ((bytes[i] & 255) < 16) {
        sb.append("0");
      }

      sb.append(Long.toString((long)(bytes[i] & 255), 16));
    }

    return sb.toString().toUpperCase();
  }

  public static String md5LowerCase(String text, String salt) throws Exception {
    byte[] bytes = (text + salt).getBytes();
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(bytes);
    bytes = messageDigest.digest();
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < bytes.length; ++i) {
      if ((bytes[i] & 255) < 16) {
        sb.append("0");
      }

      sb.append(Long.toString((long)(bytes[i] & 255), 16));
    }

    return sb.toString().toLowerCase();
  }

  public static boolean verify(String text, String salt, String md5) throws Exception {
    String md5Text = md5UpperCase(text, salt);
    return md5Text.equalsIgnoreCase(md5);
  }
}
