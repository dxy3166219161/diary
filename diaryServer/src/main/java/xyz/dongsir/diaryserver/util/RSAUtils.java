package xyz.dongsir.diaryserver.util;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 加密工具类
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/26 14:29     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class RSAUtils {

    @Value("${web.param.encrypt.privatekey}")
    public String deployPrivateKey;

    @Value("${web.param.encrypt.publickey}")
    public String deployPublicKey;


    public static final String KEY_ALGORITHM = "RSA";
    /** 貌似默认是RSA/NONE/PKCS1Padding，未验证 */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /** RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024 */
    public static final int KEY_SIZE = 2048;

    /**
     * 生成公/私密钥
     * @return
     */
    public Map<String,String> generateKey(){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyStr = (new BASE64Encoder()).encodeBuffer(publicKey.getEncoded());
        String privateKeyStr = (new BASE64Encoder()).encodeBuffer(privateKey.getEncoded());
        Map<String,String> keyMap = new HashMap<>();
        keyMap.put("publicKey",publicKeyStr);
        keyMap.put("privateKey",privateKeyStr);
        return keyMap;
    }

//    /**
//     * 生成密钥对。注意这里是生成密钥对KeyPair，再由密钥对获取公私钥
//     *
//     * @return
//     */
//    public static Map<String, byte[]> generateKeyBytes() {
//
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator
//                    .getInstance(KEY_ALGORITHM);
//            keyPairGenerator.initialize(KEY_SIZE);
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//
//
//            String s = (new BASE64Encoder()).encodeBuffer(publicKey.getEncoded());
//            String s1 = (new BASE64Encoder()).encodeBuffer(privateKey.getEncoded());
//            System.out.println(s);
//            System.out.println(s1);
//
//            Map<String, byte[]> keyMap = new HashMap<String, byte[]>();
//            keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
//            keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
//            return keyMap;
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
     *
     * @param keyBytes
     * @return
     */
    public PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
     *
     * @param keyBytes
     * @return
     */
    public PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory
                    .generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密，三步走。
     *
     * @param key
     * @param plainText
     * @return
     */
    public byte[] RSAEncodePublicKey(PublicKey key, byte[] plainText) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 私钥加密
     * @param key
     * @param plainText
     * @return
     */
    public byte[] RSAEncodePrivateKey(PrivateKey key, byte[] plainText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 私钥解密，三步走。
     *
     * @param key
     * @param encodedText
     * @return
     */
    public String RSADecodePrivateKey(PrivateKey key, byte[] encodedText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(encodedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥解密，三步走。
     *
     * @param key
     * @param encodedText
     * @return
     */
    public String RSADecodePublicKey(PublicKey key, byte[] encodedText) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(encodedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    
    /** 
     * @description: RSA 私钥加密
     * @param: [text] 
     * @return: java.lang.String 
     * @author dongxingyu
     * @date: 2021/11/26 15:19
     */ 
    public String encryptPrivateKey(String text){
        try {
            byte[] privateBytes = (new BASE64Decoder()).decodeBuffer(deployPrivateKey);
            PrivateKey privateKey = restorePrivateKey(privateBytes);
            byte[] encodedText = RSAEncodePrivateKey(privateKey, text.getBytes());
            String ciphertext = Base64.encodeBase64String(encodedText);
            return ciphertext;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: RSA 公钥加密
     * @param: [text]
     * @return: java.lang.String
     * @author dongxingyu
     * @date: 2021/11/26 15:19
     */
    public String encryptPublicKey(String text){
        try {
            byte[] publicBytes = (new BASE64Decoder()).decodeBuffer(deployPublicKey);
            PublicKey publicKey = restorePublicKey(publicBytes);
            byte[] encodedText = RSAEncodePublicKey(publicKey, text.getBytes());
            String ciphertext = Base64.encodeBase64String(encodedText);
            return ciphertext;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * @description: RSA 公钥 解密
     * @param: [text] 
     * @return: java.lang.String 
     * @author dongxingyu
     * @date: 2021/11/26 15:20
     */ 
    public String decryptPublicKey(String text){
        try {
            byte[] publicBytes = (new BASE64Decoder()).decodeBuffer(deployPublicKey);
            PublicKey publicKey = restorePublicKey(publicBytes);
            byte[] bytes = Base64.decodeBase64(text);
            String decodePublicKey = RSADecodePublicKey(publicKey, bytes);
            return decodePublicKey;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: RSA 私钥 解密
     * @param: [text]
     * @return: java.lang.String
     * @author dongxingyu
     * @date: 2021/11/26 15:20
     */
    public String decryptPrivateKey(String text){
        try {
            byte[] privateKeyByte = (new BASE64Decoder()).decodeBuffer(deployPrivateKey);
            PrivateKey privateKey = restorePrivateKey(privateKeyByte);
            byte[] bytes = Base64.decodeBase64(text);
            String decodePublicKey = RSADecodePrivateKey(privateKey, bytes);
            return decodePublicKey;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        deployPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhibKzrXY9jAHHCCS7iZy/t2hsTfUrEfC\n" +
//                "+scPflBXt3XZHbKLgrY4ffsJCVgINPYVK3y5hTzM/rWiLOiw4IwGyRRY4dafQcuMFpcjPg18GsBh\n" +
//                "MRLo2nBRASNGYX1QM5WVRhRlMiJlBaP/JTtqQhhxN+9trsm5GFunnm0OFFeBlA+fid9Aav13b2ZA\n" +
//                "iVTeccwCL6ul7ytDc4LX+4DYfu2orFn+Gjt3Yr04amh2RGW0bb+iQ7L7y/7DofABngNpo0/WPqVw\n" +
//                "pbUNcf1UYhDiKIqbN+4SrG3kkwBPsVd8qnLjpSji3chooHI14ybTXC/1535Wp6GzKdP3POyFf3qz\n" +
//                "gPqsCwIDAQAB";
//        deployPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGJsrOtdj2MAccIJLuJnL+3aGx\n" +
//                "N9SsR8L6xw9+UFe3ddkdsouCtjh9+wkJWAg09hUrfLmFPMz+taIs6LDgjAbJFFjh1p9By4wWlyM+\n" +
//                "DXwawGExEujacFEBI0ZhfVAzlZVGFGUyImUFo/8lO2pCGHE3722uybkYW6eebQ4UV4GUD5+J30Bq\n" +
//                "/XdvZkCJVN5xzAIvq6XvK0Nzgtf7gNh+7aisWf4aO3divThqaHZEZbRtv6JDsvvL/sOh8AGeA2mj\n" +
//                "T9Y+pXCltQ1x/VRiEOIoips37hKsbeSTAE+xV3yqcuOlKOLdyGigcjXjJtNcL/XnflanobMp0/c8\n" +
//                "7IV/erOA+qwLAgMBAAECggEAcZiRrX5w7O74ZknzATSPtd9o1s8HCKdvpLlBFl3kGRbyteIFnra8\n" +
//                "mXkkSsQ/ltE2Ve+0jO1STPDzFgIoHMyVxlmzdfEuRCGfS1XJ4va6O1DiPBLeGHJ5NXRwWYcqjbPB\n" +
//                "hmko084qKx+Woylvre5SmOHmx9ZLJSQfiNihznnW4M8o5kA/YFQq3Sgg7M92TChOiBVh7o5KjyGl\n" +
//                "WQQtT9LjZSzWJ8j5C/Bsp3fHBDOIgGGjwR77d7jlBL8dPLc9b3co0VE+q/4Ad+8kUaeLENMbMdJs\n" +
//                "hcuO4eU6pUb84zItu+zKwbef3N3mgP2FLGzIpu65bRfeKVF0sjfHq1KTvqnEyQKBgQDVuUjS7Xp3\n" +
//                "JQI/V85mrDUIVOT5DGd9SWQJygFpFTkDXxzwcZW7BpIe9ATd1Kk8ebnVaZ2PS+G1V5gfO66WPjB7\n" +
//                "E6s511009IKg+4FiTkgMK0nZ9eWYs9v0QeGuzDG5Ym17CcCtyEIk81i+Y4Xd0FF6X3zGP6b7T85z\n" +
//                "VYrCHmPm5QKBgQCgsBCpK8VeLZUStG1ozed9FY9vKxrvzL4SmM3cGuZ+OqRuBtS9G5VzOSdaCBHX\n" +
//                "3087/9HI7tyFUBOhSSyfXbO1tc+REpqHi69CrXfRQvhnfjTvbMGBB7QwW0oB3+auTIS6lDnDw4lM\n" +
//                "gogPXLZEfNSvdmSigqbjTaup7DkZwdmoLwKBgA4e4crd2duJXG6m9IIgGpjkUwSJZ0UF1lA3QoSY\n" +
//                "3F12qUjRmFz1AC5/XNrzNANE35xUEA7pqo36rZhSryRKE5XuJL/7lLLs/Lx6IYBHP8deDNETyAmg\n" +
//                "yIWI3MpZv2dKM4WPjWVt98DNOy3JEY0oOFnneSz4A6ZTDYbdPf+5VkxpAoGAExSVBeGCsCotd2kh\n" +
//                "TL6WbE3xGDxAGJVxRHgMicRP9lE0UTRScpoh5uq0d4ZZyA5mNDOuT3O9j77wdchgmhPgUQVjvlA9\n" +
//                "NTcq+nnqrliLco6T7pI1cALgLJXKdeqGU9AWOql7Knm/hexCB8D0i7Fe9+7RCFMHShpg9e5s4VgZ\n" +
//                "ZlUCgYEAyZNSlsp7lQ/L6hc6FMki0nFTCRjr7iW9E/ZzFgayOIx7Lq2W+Whk4mHM4XMtG7hLGIuv\n" +
//                "gbMS1yars+EnNksPDuQm8V7pdbr4B1ZjyYb3eUdxuy+yGqxjZgdo343tWJjwszomXUWoH9Two9ev\n" +
//                "jaIzCWpWu1zoiHx+sydD4itzgcE=";
//
//        // 公钥加密，私钥解密 - 密文由前端提供
//        String encrypted = "bbwyVYOaOBFY2tCQs6FIQumeBD53BvOsUlp+3WACRFww1s9otq2fGTKcdiJwJUD5EHPXWIZSFO3rY88euMtkddUvwM0rXOvo8onIqImyAD1vTlMSUngNdtQeeBDIe6a+b6WgBqP2b547fgIflR97SvIkuiea2COnc8gD8LaMiXu8IK39N+NJFBanHHyv04SoNRDsl+ACQAI15bdzGd///1BvOx9TKwQ/GhjWoB55SIERp7iImZc7ITKNghaDk9T+xTr0U8V0Fm5u+8zp+epcxltuTZOQIjxcbZKAwnDS/LZhtuED0UAHAUtmJZr/sNJ6vHrh4IUWo7b4JjA9ljFLtg==";
//        String decryptionResult = decryptPrivateKey(encrypted);
//        System.out.println("前端提供密文解密结果：" + decryptionResult);
//
//        // 公钥加密，私钥解密
//        String ciphertext = "公钥加密，私钥解密";
//        String encryptedRes = encryptPublicKey(ciphertext);
//        System.out.println("公钥加密，私钥解密----加密结果：" + encryptedRes);
//        String decryptionRes = decryptPrivateKey(encryptedRes);
//        System.out.println("公钥加密，私钥解密----解密结果：" + decryptionRes);
//
//        // 私钥加密，公钥解密
//        String ciphertext2 = "私钥加密，公钥解密";
//        String encryptedRes2 = encryptPrivateKey(ciphertext2);
//        System.out.println("私钥加密，公钥解密----加密结果：" + encryptedRes2);
//        String decryptionRes2 = decryptPublicKey(encryptedRes2);
//        System.out.println("私钥加密，公钥解密----解密结果：" + decryptionRes2);
    }
}