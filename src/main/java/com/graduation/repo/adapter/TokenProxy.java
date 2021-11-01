package com.graduation.repo.adapter;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Description 用于token的简单保护代理
 *
 * @author shaoming
 * @date 2021/11/1 10:49
 * @since 1.0
 */
public class TokenProxy {

    private static final String SALT = "ThisIsTokenSalt";

    private static final Integer COUNT = 3;

    private static final byte[] SALT_BYTES = SALT.getBytes(StandardCharsets.UTF_8);

    private static boolean isValid(int b) {
        if (b >= 48 && b <= 57) {
            return true;
        }
        if (b >= 97 && b <= 122) {
            return true;
        }
        return false;
    }

    private static int getLeftBound(byte b) {
        if (b >= 48 && b <= 57) {
            return 48;
        }
        if (b >= 97 && b <= 122) {
            return 97;
        }
        return 1;
    }

    private static String encode(String token) {
        byte[] tokenBytes = token.getBytes(StandardCharsets.UTF_8);
        int len = tokenBytes.length;
        int b;
        int index;
        int mod;
        for (int i = 0; i < SALT_BYTES.length; i++) {
            index = SALT_BYTES[i] % getLeftBound(SALT_BYTES[i]);
            if (index >= len) {
                mod = index % len;
            }else {
                mod = index;
            }
            if (mod == len) {
                mod -= 1;
            } else {
                mod = index;
            }
            b = tokenBytes[mod] + index;
            if (isValid(b)) {
                tokenBytes[mod] = (byte) b;
            }
        }
        return new String(tokenBytes, StandardCharsets.UTF_8);
    }

    private static String decode(String encoded) {
        byte[] encodedBytes = encoded.getBytes(StandardCharsets.UTF_8);
        int len = encodedBytes.length;
        int b;
        int index;
        int mod;
        for (int i = 0; i < SALT_BYTES.length; i++) {
            index = SALT_BYTES[i] % getLeftBound(SALT_BYTES[i]);
            if (index >= len) {
                mod = index % len;
            } else {
                mod = index;
            }
            if (mod == len) {
                mod -= 1;
            }
            b = encodedBytes[mod] - index;
            if (isValid(b)) {
                encodedBytes[mod] = (byte) b;
            }
        }
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String tokenEncode(String token) {
        String content = encode(token);
        for (int i = 1; i < COUNT; i++) {
            content = encode(content);
        }
        return content;
    }

    public static String tokenDecode(String token) {
        String content = decode(token);
            for (int i = 1; i < COUNT; i++) {
                content = decode(content);
            }
        return content;
    }

    public static void main(String[] args) {
        String s = "380104eeca293cd6e3e7d09dcb5aa18e";
        System.out.println(s);
        String tokenEncode = tokenEncode(s);
        System.out.println(tokenEncode);
        String tokenDecode = tokenDecode(s);
        System.out.println(tokenDecode);
    }
}
