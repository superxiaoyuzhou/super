package com.example.demo.ThreeDES;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;


/**
 * 3DES是对DES对称加密算法改进后的一种对称加密算法,它使用3条56位的密钥对数据进行三次加密。。
 * 3DES加密和解密过程中，密钥长度都必须是8的倍数
 * 密码，长度要是8的倍数
 */
public class TestDES {
    /**
     * 加密
     *
     * @param data
     * @param password
     * @return
     */
    public static String DESEncode(String data, String password) {
        try {
            //1.构造密钥生成器，指定为DES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("DESede");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            //DES加密和解密过程中，密钥长度都必须是8的倍数
            kgen.init(new SecureRandom(password.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
//            //创建一个密匙工厂，然后用它把DESKeySpec转换成
//            DESKeySpec desKey = new DESKeySpec(password.getBytes());
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey secretKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //现在，获取数据进行加密并进行编码转换
            return Base64.encode(cipher.doFinal(data.getBytes("utf-8")));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param data
     * @param password
     * @return
     */
    public static String DESDecode(String data, String password) {
        try {
            //1.构造密钥生成器，指定为DES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("DESede");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(new SecureRandom(password.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
//            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            //获取密码器
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            //初始化密码密器
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //解密并进行编码转换
            return new String(cipher.doFinal(Base64.decode(data)), "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        //密码，长度要是8的倍数
        String password = "12345678";
        String data = "看了没啥用的文字！";
        System.out.println("数据：" + data);
        String dataEncode = DESEncode(data, password);
        System.out.println("加密后：" + dataEncode);
        String dataDecode = DESDecode(dataEncode, password);
        System.out.println("解密后：" + dataDecode);
    }
}
