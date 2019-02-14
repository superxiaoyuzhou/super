package com.example.demo.DES;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * DES加密介绍
     DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
     后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
     24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现.
 */
public class DESUtil {
    private static final String KEY_INSTANCE = "DES";
    private static final String SEED = "12345678";
    private static Logger logger = Logger.getLogger(DESUtil.class.getName());

    public static String DESEncode(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(KEY_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE,getKey());
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("utf-8")));
    }
    public static String DESDecode(String dataEncode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] data = Base64.getDecoder().decode(dataEncode);
        Cipher cipher = Cipher.getInstance(KEY_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE,getKey());
        return new String(cipher.doFinal(data),"utf-8");
    }
    public static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_INSTANCE);
        keyGenerator.init(new SecureRandom(SEED.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static void main(String[] args) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        logger.info("种子:" + SEED);
        String data = "看了也没什么用！";
        System.out.println("加密数据：" + data);
        String dataEncode = DESUtil.DESEncode(data);
        System.out.println("加密后：" + dataEncode);
        String dataDecode = DESUtil.DESDecode(dataEncode);
        System.out.println("解密后：" + dataDecode);
    }
}
