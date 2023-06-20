package io.github.yuazer.zaxlib.SocketUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class URLCrypto {
    public static String encryptURL(String url) {
        byte[] encodedBytes = Base64.getEncoder().encode(url.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String decryptURL(String encryptedURL) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedURL.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
