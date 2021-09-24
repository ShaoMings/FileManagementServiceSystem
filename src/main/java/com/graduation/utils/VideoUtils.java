package com.graduation.utils;

import com.graduation.exception.FileConverterException;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;

/**
 * Description 用于将视频格式转换为mp4格式 或者进行视频压缩的工具类
 *
 * @author shaoming
 * @date 2021/8/29 09:23
 * @since 1.0
 */
public class VideoUtils {


    /**
     * 将视频文件转为mp4格式字节数组
     * @param source 源文件
     * @return  字节数组
     */
    @Deprecated
    public static byte[] convertVideoToMp4Bytes(File source){
        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec("libmp3lame");
        audioAttributes.setBitRate(6400);
        audioAttributes.setChannels(1);
        audioAttributes.setSamplingRate(22050);

        VideoAttributes videoAttributes = new VideoAttributes();
        videoAttributes.setCodec("mpeg4");
        videoAttributes.setBitRate(160000);
        videoAttributes.setFrameRate(30);

        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setOutputFormat("mp4");
        encodingAttributes.setAudioAttributes(audioAttributes);
        encodingAttributes.setVideoAttributes(videoAttributes);
        File target = new File(Constant.OUTPUT_TMP_FILE_PATH+"tmp.mp4");
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source),target,encodingAttributes);
            return AudioConverter.getBytesFromFile(target);
        } catch (EncoderException e) {
            e.printStackTrace();
        }finally {
            DocumentConverter.deleteFile(source,target);
        }
        throw new FileConverterException("转换为mp4格式失败!");
    }


    /**
     *  对视频文件进行压缩 并输出mp4格式对字节数组
     * @param source 源文件
     * @return  字节数组
     */
    @Deprecated
    public static byte[] compressVideo(File source){
        // 音频编码设置
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(64000);
        audio.setChannels(1);
        audio.setSamplingRate(22050);

        // 视频编码设置
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(160000);
        video.setFrameRate(25);

        // 视频转码编码设置
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        File target = new File(Constant.OUTPUT_TMP_FILE_PATH+"tmp.mp4");
        // 编码器
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source),target,attrs);
            return AudioConverter.getBytesFromFile(target);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DocumentConverter.deleteFile(source,target);
        }
        throw new FileConverterException("压缩视频文件失败!");
    }

    public static void main(String[] args) {
        File source = new File(Constant.OUTPUT_TMP_FILE_PATH + "test.mkv");
        convertVideoToMp4Bytes(source);
    }
}
