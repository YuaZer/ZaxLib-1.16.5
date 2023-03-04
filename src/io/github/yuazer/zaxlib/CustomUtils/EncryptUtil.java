package io.github.yuazer.zaxlib.CustomUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
    // AES 加密算法
    private static final String AES = "AES";

    /**
     * 生成 AES 密钥
     * @return 密钥字符串
     * @throws NoSuchAlgorithmException
     */
    public static String generateAESKey() throws NoSuchAlgorithmException {
        // 生成一个 128 位的 AES 密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        return Base64.getUrlEncoder().encodeToString(keyBytes);
    }

    /**
     * 使用 AES 加密字符串
     * @param plainText 明文字符串
     * @param key 密钥字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptAES(String plainText, String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 使用 AES 解密字符串
     * @param encryptedText 加密后的字符串
     * @param key 密钥字符串
     * @return 明文字符串
     * @throws Exception
     */
    public static String decryptAES(String encryptedText, String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] plainTextBytes = cipher.doFinal(encryptedBytes);
        return new String(plainTextBytes, StandardCharsets.UTF_8);
    }
}
