package com.graduation.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Description AES加解密工具类
 *
 * @author shaoming
 * @date 2021/8/30 15:44
 * @since 1.0
 */
public class AesUtils {
    /**
     * 参数分别代表 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content    加密的字符串
     * @param encryptKey key值
     * @return 加密后的密文
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return 解密后的明文
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 指定加密方式对明文加密
     *
     * @param content 明文
     * @return 加密后的密文
     */
    public static String encrypt(String content) throws Exception {
        return encrypt(content, Constant.AES_KEY);
    }

    /**
     * 指定解密方式对密文进行解密
     *
     * @param encryptStr 密文
     * @return 明文
     */
    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, Constant.AES_KEY);
    }


    /**
     * 通过密文获取校验码
     *
     * @param encryptStr 密文
     * @return 校验码 提取码
     */
    public static String getCheckCodeByEncryptStr(String encryptStr) {
        return getCheckCode(encryptStr);
    }

    /**
     * 通过明文获取校验码
     *
     * @param decryptStr 明文
     * @return 校验码 提取码
     */
    public static String getCheckCodeByDecryptStr(String decryptStr) {
        try {
            return getCheckCode(encrypt(decryptStr,Constant.AES_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取校验码
     *
     * @param encryptStr 密文
     * @return 校验码
     */
    private static String getCheckCode(String encryptStr) {
        int len = encryptStr.length();
        int lastPartIndex = (len * 2) / 3;
        int begin = lastPartIndex + encryptStr.charAt(len - 2) % (len - lastPartIndex);
        if (begin >= len) {
            begin = lastPartIndex + (begin % lastPartIndex);
        }
        char a = encryptStr.charAt(begin);
        if (begin < (lastPartIndex / 2)) {
            begin += (len % (len - lastPartIndex));
        }
        int tmp = begin;
        a = getChar(encryptStr, a, tmp);
        begin -= (a % 9);
        char b = encryptStr.charAt(begin);
        tmp = begin;
        b = getChar(encryptStr, b, tmp);
        begin -= (b % 9);
        char c = encryptStr.charAt(begin);
        tmp = begin;
        c = getChar(encryptStr, c, tmp);
        begin -= (c % 9);
        char d = encryptStr.charAt(begin);
        tmp = begin;
        d = getChar(encryptStr, d, tmp);
        return String.valueOf(new char[]{a, b, c, d});
    }

    private static char getChar(String encryptStr, char c, int tmp) {
        while (!isUsefulChar(c)) {
            if (tmp >= 0) {
                c = encryptStr.charAt(tmp);
                tmp--;
            } else {
                break;
            }
        }
        return isUsefulChar(c) ? c : 'Q';
    }

    private static boolean isUsefulChar(char t) {
        boolean f1 = 48 <= t && t <= 57;
        boolean f2 = 65 <= t && t <= 90;
        boolean f3 = 97 <= t && t <= 122;
        return f1 || f2 || f3;
    }


    public static String getTokenByCode(String code) throws Exception {
        String path = decrypt(code);
        int count = path.length() - path.replaceAll("/","").length();
        String filePath;
        if (count>1){
            filePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
            String username = path.substring(0, path.indexOf("/"));
            return TokenUtils.getShareAuthToken(AesUtils.getCheckCodeByDecryptStr("/" + username + filePath.substring(1)));
        }else {
            filePath = path.substring(path.indexOf("/"),path.lastIndexOf("@"));
            return TokenUtils.getShareAuthToken(AesUtils.getCheckCodeByDecryptStr(filePath));
        }
    }

    public static void main(String[] args) throws Exception {
        String content = "/admin/test/doc/Chapter_2.pdf@" + DateConverter.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss");

        System.out.println("加密前：" + content);

//        String encrypt = encrypt(content, Constant.AES_KEY);
//        System.out.println("加密后：" + encrypt);
//
//        String decrypt = decrypt(encrypt, Constant.AES_KEY);
//        System.out.println("解密后：" + decrypt);
//        System.out.println(getCheckCodeByEncryptStr(encrypt));
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

// 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("加密后："+encryptHex);
// 解密
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密后：" + decryptStr);

    }
}
