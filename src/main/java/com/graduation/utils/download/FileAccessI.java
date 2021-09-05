package com.graduation.utils.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/5 11:06
 * @since 1.0
 */
public class FileAccessI implements Serializable {

    RandomAccessFile oSavedFile;
    long nPos;
    private String fileNamePath;
    SiteFileFetch siteFileFetch;

    public FileAccessI() throws IOException {
        this(null,"", 0);
    }

    public FileAccessI(SiteFileFetch siteFileFetch,String sName, long nPos) throws IOException {
        this.siteFileFetch = siteFileFetch;
        this.fileNamePath = sName;
//        oSavedFile = new RandomAccessFile(sName, "rw");
//        this.nPos = nPos;
//        oSavedFile.seek(nPos);
    }

    public synchronized int write(byte[] b, int nStart, int nLen) {
        int n = -1;
        try {
            // 保存到本地
//            oSavedFile.write(b, nStart, nLen);
            // 根据传入的输出流保存
            siteFileFetch.siteInfoBean.fileOutputStream.write(b, nStart, nLen);
            n = nLen;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }


    public void deleteFile(){
        File savedFile = new File(fileNamePath);
        if (savedFile.exists()){
            savedFile.delete();
        }
    }
}