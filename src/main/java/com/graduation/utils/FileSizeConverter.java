package com.graduation.utils;

import java.text.DecimalFormat;

/**
 * Description 用于不同的文件大小 单位之间的转换
 *
 * @author shaoming
 * @date 2021/8/16 17:34
 * @since 1.0
 */
public class FileSizeConverter {

    /**
     * 根据文件大小自动转为合适的单位(KB,MB,GB)
     *
     * @param fileSize 文件的大小（long型）
     * @return 转换后带单位的文件大小字符串
     */
    public static String getLength(long fileSize) {
        String strFileSize = Constant.NULL_VALUE;
        // 小于k的文件单位
        if (fileSize < Constant.SIZE_K) {
            strFileSize = fileSize + "B";
        } else {
            DecimalFormat df = new DecimalFormat("######0.00");
            if (fileSize < Constant.SIZE_K * Constant.SIZE_K) {
                strFileSize = df.format(((double) fileSize) / Constant.SIZE_K) + "KB";
            } else if ((fileSize >= Constant.SIZE_K * Constant.SIZE_K) && (fileSize < Constant.SIZE_K * Constant.SIZE_K * Constant.SIZE_K)) {
                strFileSize = df.format(((double) fileSize) / (Constant.SIZE_K * Constant.SIZE_K)) + "MB";
            } else {
                strFileSize = df.format(((double) fileSize) / (Constant.SIZE_K * Constant.SIZE_K * Constant.SIZE_K)) + "GB";
            }
        }
        return strFileSize;
    }


    /**
     * 通过传入的存储大小字符串 包含单位 自动转为Byte 的长度
     * @param size 存储大小字符串 包含单位
     * @return Byte 的长度
     */
    public static Double getLengthAutoCalToByte(String size) {
        double sizeLen;
        if (size.endsWith("KB")){
            sizeLen = Double.parseDouble(size.replace("KB","")) * Constant.SIZE_K;
        } else if (size.endsWith("MB")) {
            sizeLen = Double.parseDouble(size.replace("MB","")) * Constant.SIZE_K * Constant.SIZE_K;
        } else if (size.endsWith("GB")){
            sizeLen = Double.parseDouble(size.replace("GB","")) * Constant.SIZE_K * Constant.SIZE_K * Constant.SIZE_K;

        }else {
            sizeLen = Double.parseDouble(size.replace("B",""));
        }
        return sizeLen;
    }


    /**
     * 将文件大小转为mb为单位的大小字符串
     *
     * @param filesize 文件的大小（long型）
     * @return 转换后不带单位的文件大小字符串 默认的单位是mb
     */
    public static String getLengthOfMb(long filesize) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(((double) filesize) / (Constant.SIZE_K * Constant.SIZE_K));
    }
}
