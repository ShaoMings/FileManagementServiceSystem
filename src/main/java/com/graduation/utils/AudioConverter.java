package com.graduation.utils;


import com.graduation.exception.FileConverterException;
import ws.schild.jave.*;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;


import java.io.*;


/**
 * @author shaoming
 */
public class AudioConverter {

    /**
     * 将文件file对象转为字节数组
     * @param target 文件file
     * @return 字节数组
     */
    public static byte[] getBytesFromFile(File target){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(target);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new FileConverterException("文件格式转换失败!");
    }

    /**
     * 将任意的音频格式转为mp3格式字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] convertAnyAudioToMp3Bytes(File source){
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp3");
        attrs.setAudioAttributes(audio);
        File target = new File(Constant.OUTPUT_TMP_FILE_PATH+"tmp.mp3");
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source), target, attrs);
            return getBytesFromFile(target);
        } catch (EncoderException e) {
            e.printStackTrace();
        } finally {
            DocumentConverter.deleteFile(source,target);
        }
        throw new FileConverterException("文件格式转换失败!");
    }


    /**
     * 将m4a格式文件转为wav格式字节数组
     *
     * @param source 源文件
     * @return 字节数组
     */
    @Deprecated
    public static byte[] m4aToWavBytes(File source) {
        AudioAttributes audio = new AudioAttributes();
        // 音频属性设置
        audio.setCodec("pcm_s16le");
        audio.setBitRate(16000);
        audio.setChannels(1);
        audio.setSamplingRate(16000);
        // 编码属性设置
        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setOutputFormat("wav");
        encodingAttributes.setAudioAttributes(audio);
        File target = new File(Constant.OUTPUT_TMP_FILE_PATH+"tmp.wav");
        // 编码
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source), target, encodingAttributes);
            return getBytesFromFile(target);
        } catch (EncoderException e) {
            e.printStackTrace();
        } finally {
            DocumentConverter.deleteFile(source,target);
        }
        throw new FileConverterException("文件格式转换失败!");
    }


    /**
     *  '将m4a格式文件转为mp3格式字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] m4aToMp3Bytes(File source) {
        return convertAnyAudioToMp3Bytes(source);
    }


    /**
     *  '将wav格式文件转为mp3格式字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] wavToMp3Bytes(File source) {
        return convertAnyAudioToMp3Bytes(source);
    }

    public static void main(String[] args) throws IOException {
        File file = new File(Constant.OUTPUT_TMP_FILE_PATH+"test.m4a");
        byte[] bytes = m4aToMp3Bytes(file);

    }
}
