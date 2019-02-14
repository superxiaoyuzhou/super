package com.example.demo.Test;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.对称加解密算法
 * a.通信双方同时掌握一个密钥，加密解密都是由一个密钥完成的（即加密密钥等于解密密钥，加解密密钥可以相互推倒出来）。
 * b.双方通信前共同拟定一个密钥，不对第三方公开。
 * c.不具有个体原子性，一个密钥被共享，泄漏几率增大
 * 2.公私钥加解密算法
 * a.通信双方掌握不同的密钥，不同方向的加解密由不同的密钥完成。
 * 3.块加密的模式？ ECB（电码本模式），CBC（加密块链模式），OFB（输出反馈模式），CFB（加密反馈模式）等
 * 4.KeyGenerator， KeyPairGenerator，KeyFactory，SecretKeyFactory这四个类的区别：
 * KeyGenerator和SecretKeyFactory，都是javax.crypto包的，生成的key主要是
 * 提供给AES，DES，ThreeDES，MD5,SHA1等对称和单向 加密算法。
 * KeyPairGenerator和KeyFactory，都是java.security包的，生成的key主要是提供给DSA，RSA， EC等 非对称加密算法。
 */
public class demo {
    public static void printKey(byte[] keyBytes) {
        try {
            //第一种，Factory
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key1 = keyFactory.generateSecret(keySpec);

            //第二种, Generator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56, new SecureRandom(keyBytes));//key为8个字节，实际用了56位； 后面随机数用key作为种子seed生成
            SecretKey key2 = keyGenerator.generateKey();

            //第三种， SecretKeySpec
            SecretKey key3 = new SecretKeySpec(keyBytes, "DES");//SecretKeySpec类同时实现了Key和KeySpec接口

            //打印
            System.out.println("key1：");
            System.out.println("key2：");
            System.out.println("key3：");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 对于解密，一般来说加密的数据长度本身就是8的倍数，所以只需要NoPadding就可以了，
     * 若加密的数据长度不是8，就需要用PKCS5Padding，否则解密出来后的明文尾巴的会比原明文的尾巴多出好几位填充数据。
     * （实测其它模式是，会抛 Given final block not properly padded的异常，这个要结合实际切换一下NoPadding 和 PKCS5Padding）
     *
     * @param keyBytes
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static void pattern(byte[] keyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, new SecureRandom(keyBytes));
        SecretKey key = keyGenerator.generateKey();
        //ECB模式
        //Cipher加密器初始化需要一个字符串，字符串里提供了三种设置。一是，加解密算法；二是，加解密模式；三是，是否需要填充。
        Cipher cipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, key);
        //CBC模式
        Cipher cipher2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher2.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV
        //OFB模式
        Cipher cipher3 = Cipher.getInstance("DES/OFB/PKCS5Padding");
        cipher3.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV
        //CFB模式
        Cipher cipher4 = Cipher.getInstance("DES/CFB/PKCS5Padding");
        cipher4.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV
    }

    public static void main(String[] args) throws Exception {
        String data = "看了也没有用的数据！";
        System.out.println("加密数据：" + data);
        //种子
        String seed = "12345678";
        //AES加密--解密
//        String dataEncode = AESEncode(data, seed);
//        String dataDecode = AESDecode(dataEncode, seed);

        //DES加密--解密
//        String dataEncode = DESEncode(data,seed);
//        String dataDecode = DESDecode(dataEncode,seed);

        //3DES加密--解密
//        String dataEncode = ThreeDESEncode(data,seed);
//        String dataDecode = ThreeESDecode(dataEncode,seed);

        //RSA
        Map<String, Object> keyMap = genKeyPair();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        //RSA 公钥加密--私钥解密
        String dataEncode = RSAPublicKeyEncode(data,publicKey);
        String dataDecode = RSAPrivateKeyDecode(dataEncode,privateKey);
        //RSA 私钥加密--公钥解密
//        String dataEncode = RSAPrivateKeyEncode(data,privateKey);
//        String dataDecode = RSAPublicKeyDecode(dataEncode,publicKey);

        //RSA 私钥签名--公钥验签--签名验证
        //对加密数据签名
        String sign = sign(dataEncode, privateKey);
        //对加密数据验签
        if(verify(dataEncode,publicKey,sign)){
            System.out.println("签名验证通过。");
        }else {
            System.out.println("签名验证失败！");
        }

        System.out.println("加密密文：" + dataEncode);
        System.out.println("解密名文：" + dataDecode);

    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param dataEncode 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(String dataEncode, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        //通过PKCS8对象，获取私钥对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateK);
        signature.update(Base64.getDecoder().decode(dataEncode));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicK);
        signature.update(Base64.getDecoder().decode(data));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    private static String RSAPublicKeyDecode(String dataEncode, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        //通过X509编码的Key指令获得公钥对象
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,publicK);
        byte[] dataDecode = cipher.doFinal(Base64.getDecoder().decode(dataEncode));
        return new String(dataDecode,"utf-8");
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String RSAPrivateKeyEncode(String data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        byte[] dataDecode = cipher.doFinal(data.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(dataDecode);
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get("publicKey");
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get("privateKey");
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    /**
     *
     * RSA解密
     *
     * @param dataEncode
     * @return
     */
    private static String RSAPrivateKeyDecode(String dataEncode,String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        byte[] dataDecode = cipher.doFinal(Base64.getDecoder().decode(dataEncode));
        return new String(dataDecode,"utf-8");
    }

    /**
     *
     * RSA加密
     *
     * @param data
     * @return
     */
    private static String RSAPublicKeyEncode(String data,String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        //通过X509编码的Key指令获得公钥对象
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        byte[] dataEncode = cipher.doFinal(data.getBytes("utf-8")) ;
        return Base64.getEncoder().encodeToString(dataEncode);
    }

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        //1.获取密钥器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //2.初始化密钥器
        keyPairGenerator.initialize(1024);
        //3.创建“一对”密钥
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        Map<String, Object> map = new HashMap<>();
        map.put("privateKey", keyPair.getPrivate());
        map.put("publicKey", keyPair.getPublic());
        return map;
    }

    /**
     *
     * 3DES解密
     *
     * @param dataEncode
     * @param seed
     * @return
     */
    private static String ThreeESDecode(String dataEncode, String seed) throws Exception{
        //1.构造密钥生成器，指定为DES算法,不区分大小写
        KeyGenerator kgen = KeyGenerator.getInstance("DESede");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        kgen.init(new SecureRandom(seed.getBytes()));
        //3.产生原始对称密钥
        SecretKey secretKey = kgen.generateKey();
        //获取密码器
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        //初始化密码密器
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //解密并进行编码转换
        return new String(cipher.doFinal(Base64.getDecoder().decode(dataEncode)), "utf-8");
    }

    /**
     *
     *  3DES加密
     *
     * @param data
     * @param seed
     * @return
     */
    private static String ThreeDESEncode(String data, String seed) throws Exception {
        //1.构造密钥生成器，指定为DES算法,不区分大小写
        KeyGenerator kgen = KeyGenerator.getInstance("DESede");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        //DES加密和解密过程中，密钥长度都必须是8的倍数
        kgen.init(new SecureRandom(seed.getBytes()));
        //3.产生原始对称密钥
        SecretKey secretKey = kgen.generateKey();
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //现在，获取数据进行加密并进行编码转换
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("utf-8")));
    }

    private static String DESDecode(String dataEncode, String seed) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(seed.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        //获取密码器
        Cipher cipher = Cipher.getInstance("DES");
        //初始化密码密器
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //解密并进行编码转换
        return new String(cipher.doFinal(Base64.getDecoder().decode(dataEncode)), "utf-8");

    }

    /**
     *
     *   DES加密
     *
     * @param data
     * @param seed
     * @return
     * @throws Exception
     */
    private static String DESEncode(String data,String seed) throws Exception {
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        DESKeySpec desKey = new DESKeySpec(seed.getBytes("utf-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //现在，获取数据进行加密并进行编码转换
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("utf-8")));
    }

    /**
     *
     *  AES解密
     *
     * @param dataEncode
     * @param seed
     * @return
     * @throws Exception
     */
    private static String AESDecode(String dataEncode, String seed) throws Exception {
        //1.获取并初始化密钥构造器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(seed.getBytes("utf-8")));
        //2.通过密钥构造器获取密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //3.获取并初始化密码器
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //4.通过密码器进行解密
        byte[] dataDecode = cipher.doFinal(Base64.getDecoder().decode(dataEncode));
        //5.编码转换并返回
        return new String(dataDecode, "utf-8");
    }

    private static String AESEncode(String data, String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //1.获取并初始化密钥构造器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(seed.getBytes("utf-8")));
        //2.通过密钥构造器获取密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //3.获取并初始化密码器
        Cipher cipher = Cipher.getInstance("AES");
        //ENCRYPT_MODE表示加密，DECRYPT_MODE表示解密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //4.通过密码器进行加密
        byte[] dataEncode = cipher.doFinal(data.getBytes("utf-8"));
        //5.编码转换并返回
        return Base64.getEncoder().encodeToString(dataEncode);
    }
}
