package com.graduation.utils.download;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/5 11:08
 * @since 1.0
 */
public class Utility {
    public Utility() {}

    public static void sleep(int nSecond) {
        try{
            Thread.sleep(nSecond);
        }
        catch(Exception e) {
            e.printStackTrace ();
        }
    }
    public static void log(String sMsg) {
        System.err.println(sMsg);
    }
    public static void log(int sMsg) {
        System.err.println(sMsg);
    }
}
