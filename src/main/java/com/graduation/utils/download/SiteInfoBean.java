package com.graduation.utils.download;

import java.io.OutputStream;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/5 11:07
 * @since 1.0
 */
public class SiteInfoBean {
    /**
     * 文件URL资源
     */
    private String sSiteURL;

    /**
     * 文件保存的路径(不包含文件名)
     */
    private String sFilePath;

    public volatile OutputStream fileOutputStream;

    /**
     * 文件名
     */
    private String sFileName;

    /**
     * 下载线程个数
     */
    private int nSplitter;

    public SiteInfoBean() {
        // default value of nSplitter is 5
        this("", "", "",null, 5);
    }

    /**
     * @param sURL      文件资源URL
     * @param sPath     文件保存的路径(不包含文件名)
     * @param sName     文件名
     * @param fileOutputStream 文件下载后的输出流
     * @param nSpiltter 下载线程数
     */
    public SiteInfoBean(String sURL, String sPath, String sName,OutputStream fileOutputStream, int nSpiltter) {
        sSiteURL = sURL;
        sFilePath = sPath;
        sFileName = sName;
        this.fileOutputStream = fileOutputStream;
        this.nSplitter = nSpiltter;
    }


    public String getSSiteURL() {
        return sSiteURL;
    }

    public void setSSiteURL(String value) {
        sSiteURL = value;
    }

    /**
     * 获取文件保存的路径
     *
     * @return
     */
    public String getSFilePath() {
        return sFilePath;
    }

    public void setSFilePath(String value) {
        sFilePath = value;
    }

    /**
     * 获取文件名
     *
     * @return
     */
    public String getSFileName() {
        return sFileName;
    }

    public void setSFileName(String value) {
        sFileName = value;
    }

    /**
     * 分割成的子文件个数
     *
     * @return
     */
    public int getNSplitter() {
        return nSplitter;
    }

    public void setNSplitter(int nCount) {
        nSplitter = nCount;
    }
}
