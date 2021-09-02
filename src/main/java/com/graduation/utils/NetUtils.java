package com.graduation.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * @author shaoming
 */
public class NetUtils {

    /**
     * 获取本机的内网ip地址
     * @return
     * @throws SocketException
     */
    public static String getLocalIpV4Address() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && !ip.getHostAddress().contains(":")) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && !ip.getHostAddress().contains(":")) {
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
    /**
     * 测试方法
     * 获取本机的内网ip，外网ip和指定ip的地址
     * @param args
     */
    public static void main(String[] args) {
        String ip1="";
        try {
            ip1 = NetUtils.getLocalIpV4Address();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        System.out.println("内网ip:"+ip1);

    }
}
