package com.graduation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author shaoming
 */
public class NetUtils {

    /** 接口超时时间 */
    private static final Integer TIME_OUT = 1000;

    /** 内网IP */
    public static String INTRANET_IP = getIntranetIp();
    /**  外网IP */
    public static String INTERNET_IP = getV4IP();


    private NetUtils(){}

    /**
     * 获得内网IP
     * @return 内网IP
     */
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    private static String getV4IP(){
        String ip = "";
        String chinaZ = "https://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaZ);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(TIME_OUT);
                urlConnection.setReadTimeout(TIME_OUT);
                in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            } catch (Exception e) {
                //如果超时，则返回内网ip
                return INTRANET_IP;
            }
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        String regx = "\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
        }
        if ("".equals(ip)) {
            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(getV4IP());
    }
}
