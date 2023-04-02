package io.github.yuazer.zaxlib.CustomUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesUtils {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    /**
     * 生成AES秘钥
     *
     * @param keySize 秘钥长度，可选值为128、192、256
     * @return 生成的秘钥
     */
    public static byte[] generateAesKey(int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(keySize);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * AES加密
     *
     * @param data 待加密的字符串
     * @param key  加密密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String data, byte[] key) throws Exception {
        byte[] dataBytes = data.getBytes(CHARSET);
        byte[] encryptedBytes = aes(dataBytes, key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES解密
     *
     * @param encryptedData 待解密的字符串
     * @param key           解密密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedData, byte[] key) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = aes(encryptedBytes, key, Cipher.DECRYPT_MODE);
        return new String(decryptedBytes, CHARSET);
    }

    private static byte[] aes(byte[] data, byte[] key, int mode) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(mode, keySpec);
        return cipher.doFinal(data);
    }
}
