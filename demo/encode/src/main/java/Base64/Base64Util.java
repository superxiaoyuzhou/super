package com.example.demo.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

    /**
     * 加密为字符串
     * @param encode
     * @return
     */
    public static String doEncoder(byte[] encode){
        return new BASE64Encoder().encode(encode);
    }

    /**
     * 解密为字节数组
     * @param decode
     * @return
     * @throws IOException
     */
    public static byte[] doDecoder(String decode) throws IOException {
        return new BASE64Decoder().decodeBuffer(decode);
    }

    /**
     * Base64 加密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String doEncode(String data) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
    }

    /**
     * Base64 解密
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String doDecode(String encode) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(encode),"utf-8");
    }

}
