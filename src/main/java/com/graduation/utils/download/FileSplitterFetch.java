package com.graduation.utils.download;

import java.io.*;
import java.net.*;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/5 11:05
 * @since 1.0
 */
public class FileSplitterFetch extends Thread {

    /**
     *      File URL
     *      File Snippet Start Position
     *      File Snippet End Position
     *     Thread's ID
     *      Downing is over
     *      Stop identical
     *      File Access interface
     */
    String sURL;
    long nStartPos;
    long nEndPos;
    int nThreadID;
    boolean bDownOver = false;
    boolean bStop = false;
    FileAccessI fileAccessI;

    /**
     *
     * @param sURL 文件资源URL
     * @param sName 要保存的文件名(完整路径,绝对路径)
     * @param nStart 文件指针开始位置
     * @param nEnd 文件指针结束位置
     * @param id 线程ID
     * @throws IOException
     */
    public FileSplitterFetch(SiteFileFetch siteFileFetch,String sURL, String sName, long nStart, long nEnd, int id) throws IOException {
        this.sURL = sURL;
        this.nStartPos = nStart;
        this.nEndPos = nEnd;
        nThreadID = id;
        fileAccessI = new FileAccessI(siteFileFetch,sName, nStartPos);
    }

    @Override
    public void run() {
        while (nStartPos < nEndPos && !bStop) {
            try {
                URL url = new URL(sURL);

                HttpURLConnection httpConnection = (HttpURLConnection) url
                        .openConnection();
                httpConnection.setRequestProperty("User-Agent", "NetFox");

                String sProperty = "bytes=" + nStartPos + "-";
                httpConnection.setRequestProperty("RANGE", sProperty);

//                Utility.log(sProperty);

                InputStream input = httpConnection.getInputStream();

                byte[] b = new byte[1024];

                int nRead;

                while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos && !bStop) {
                    //注意这里不用再判断 nRead+nStartPos<nEndPos,只需要 nStartPos<nEndPos就可以,
                    //因为是前面几个下载线程读取的内容超出了nEndPos,也会被它后面的子线程读取内容覆盖掉,
                    //最后一个子下载子线程最后读取到的字节个数小于1024的,所以总的结束位置不超过就可以
                    nStartPos += fileAccessI.write(b, 0, nRead);
                }

                bDownOver = true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印回应的头信息
     * @param con HttpURLConnection
     */
    public void logResponseHead(HttpURLConnection con) {
        for (int i = 1;; i++) {
            String header = con.getHeaderFieldKey(i);
            // responseHeaders.put(header,httpConnection.getHeaderField(header));
            if (header != null) {
//                Utility.log(header + " : " + con.getHeaderField(header));
            } else {
                break;
            }
        }
    }

    public void splitterStop() {
        bStop = true;
    }

}
