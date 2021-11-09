package com.graduation.utils;

/**
 * Description 一般方法工具类
 *
 * @author shaoming
 * @date 2021/10/22 15:17
 * @since 1.0
 */
public class CommonUtils {
    private CommonUtils(){}

    /**
     * 首字母大写
     * @param string
     * @return
     */
    public static String toUpperFirstChar(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        }
        return string;
    }

    /**
     * 截取第 beginCount 个 regex 和 第 endCount 个 regex 之间的字符串
     * @param beginCount 开始的次数
     * @param endCount 结束的次数
     * @param source 源串
     * @param regex 匹配正则
     * @return 目标串
     */
    public static String substringByCount(int beginCount,int endCount,String source,String regex){
        if (endCount<=beginCount){
            throw new RuntimeException("下标异常");
        }
        int beginIndex = 0;
        if (source.startsWith(regex)){
            beginCount-=1;
            endCount-=1;
            if (beginCount == 0){
                beginIndex+=1;
            }
        }
        for (int i = 0; i < beginCount; i++) {
            beginIndex = source.indexOf(regex,beginIndex+1);
        }
        int endIndex = beginIndex;
        for (int i = 0; i < endCount - beginCount; i++) {
            endIndex = source.indexOf(regex,endIndex+1);
        }
        return source.substring(beginIndex>1?(beginIndex+1):beginIndex,endIndex);
    }

    /**
     * 截取第 count 个 regex 开始后的子串
     * @param  count 次数
     * @param source 源串
     * @param regex 匹配正则
     * @return 目标串
     */
    public static String substringByCount(int count,String source,String regex){
        int beginIndex = 0;
        if (source.startsWith(regex)){
            count-=1;
        }
        for (int i = 0; i < count; i++) {
            beginIndex = source.indexOf(regex,beginIndex+1);
        }
        return source.substring(beginIndex+1);
    }


}
