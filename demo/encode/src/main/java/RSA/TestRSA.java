package com.example.demo.RSA;

import sun.security.provider.DSAKeyPairGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class TestRSA {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException {
        String data = "一支独秀";
        System.out.println("明文是:" + data);
        //1.创建密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //2.初始化生成器
        keyPairGenerator.initialize(1024);
        //3.通过生成器产生一对密钥
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //4.获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //5.获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //加密
        String encode = RSAEncode(data, publicKey);
        System.out.println("密文是："+encode);
        //解密
        String decode = RSADecode(encode, privateKey);
        System.out.println("解密后：" + decode);

    }

    public static String RSAEncode(String data,PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        //6.创建密码器
        Cipher cipher = Cipher.getInstance("RSA");
        //7.初始化密码器（初始化为加密器，设置公钥）
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        //8.通过密码器加密
        byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));
        return parseByte2HexStr(bytes);
    }
    public static String RSADecode(String data,PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        //6.创建密码器
        Cipher cipher = Cipher.getInstance("RSA");
        //7.初始化密码器（初始化为解密器，设置私钥）
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        //8.通过密码器解密
        byte[] bytes = cipher.doFinal(parseHexStr2Byte(data));
        return new String(bytes,"utf-8");
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
}
