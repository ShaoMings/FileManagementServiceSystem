package com.graduation.utils.api;

/**
 * @author shaoming
 */
public interface ProgressCallback{
    /**
     *  实时进度处理方法
     * @param progress 进度值
     */
    void progress(float progress);
}
