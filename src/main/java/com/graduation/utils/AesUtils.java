package com.graduation.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description AES加解密工具类
 *
 * @author shaoming
 * @date 2021/8/30 15:44
 * @since 1.0
 */
public class AesUtils {
    /** 参数分别代表 算法名称/加密模式/数据填充方式 */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
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
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
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

    public static String encrypt(String content) throws Exception {
        return encrypt(content, Constant.AES_KEY);
    }
    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr,  Constant.AES_KEY);
    }


    public static String getCheckCodeByEncryptStr(String encryptStr){
        return getCheckCode(encryptStr);
    }

    private static String getCheckCode(String encryptStr){
        char t = encryptStr.charAt(3);
        int begin = t % 4;
        char a = encryptStr.charAt(begin);
        int tmp = begin;
        a = getChar(encryptStr,a,tmp);
        begin +=4;
        char b = encryptStr.charAt(begin);
        tmp = begin;
       b = getChar(encryptStr,b,tmp);
        begin +=4;
        char c = encryptStr.charAt(begin);
        tmp = begin;
        c = getChar(encryptStr,c,tmp);
        begin +=4;
        char d = encryptStr.charAt(begin);
        tmp = begin;
        d = getChar(encryptStr,d,tmp);
        return String.valueOf(new char[]{a,b,c,d});
    }

    private static char getChar(String encryptStr,char c,int tmp){
        while (!isUsefulChar(c)){
            if (tmp<encryptStr.length()){
                c = encryptStr.charAt(tmp);
                tmp++;
            }else {
                break;
            }
        }
        return c;
    }

    private static boolean isUsefulChar(char t){
        boolean f1 = 48<= t && t <=57;
        boolean f2 = 65<= t && t <=90;
        boolean f3 = 97<= t && t <=122;
        return f1 || f2 || f3;
    }

    public static void main(String[] args) throws Exception {
        String content = "/admin/test/doc/Chapter_2.pdf@2021-08-31 20:53";

        System.out.println("加密前：" + content);

        String encrypt = encrypt(content, Constant.AES_KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt, Constant.AES_KEY);
        System.out.println("解密后：" + decrypt);
        System.out.println(getCheckCodeByEncryptStr(encrypt));
    }
}
