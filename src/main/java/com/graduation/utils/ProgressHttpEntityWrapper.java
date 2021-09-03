package com.graduation.utils;

import com.graduation.utils.api.ProgressCallback;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Description 文件上传实时进度处理工具类
 *
 * @author shaoming
 * @date 2021/9/3 15:13
 * @since 1.0
 */
public class ProgressHttpEntityWrapper extends HttpEntityWrapper {

    private final ProgressCallback progressCallback;
    private final Long contentLength;

    /**
     * Creates a new entity wrapper.
     *
     * @param wrappedEntity the entity to wrap.
     */
    public ProgressHttpEntityWrapper(HttpEntity wrappedEntity, final ProgressCallback progressCallback,Long contentLength) {
        super(wrappedEntity);
        this.progressCallback = progressCallback;
        this.contentLength = contentLength;
    }

    static class ProgressFilterOutputStream extends FilterOutputStream {
        private final ProgressCallback progressCallback;
        private long transferred;
        private final long totalBytes;



        public ProgressFilterOutputStream(final OutputStream out, final ProgressCallback progressCallback, final long totalBytes) throws FileNotFoundException {
            super(out);
            this.progressCallback = progressCallback;
            this.transferred = 0;
            this.totalBytes = totalBytes;
        }

        private float getCurrentProgress() {
            return ((float) this.transferred / this.totalBytes) * 100;
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            this.transferred++;
            this.progressCallback.progress(getCurrentProgress());
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            this.transferred+=len;
            this.progressCallback.progress(getCurrentProgress());
        }
    }

    @Override
    public void writeTo(OutputStream outStream) throws IOException {
        this.wrappedEntity.writeTo(outStream instanceof ProgressFilterOutputStream ? outStream :
                new ProgressFilterOutputStream(outStream,this.progressCallback,contentLength));
    }
}
