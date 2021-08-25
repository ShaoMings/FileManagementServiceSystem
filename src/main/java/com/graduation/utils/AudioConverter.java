package com.graduation.utils;


import com.graduation.exception.FileConverterException;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.*;


/**
 * @author shaoming
 */
public class AudioConverter {

    /**
     * 将m4a格式文件转为wav格式字符数组
     * @param source 源文件
     * @return 字符数组
     */
    public static byte[] m4aToWavBytes(File source){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
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
        File target = new File("src/main/java/com/graduation/utils/tmp/tmp.wav");
        // 编码
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source),target,encodingAttributes);
            fileInputStream = new FileInputStream(target);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            return outputStream.toByteArray();
        } catch (EncoderException | IOException e) {
            e.printStackTrace();
        } finally {
            if (source.exists()){
                source.delete();
            }
            if (target.exists()){
                target.delete();
            }
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new FileConverterException("文件格式转换失败!");
    }


    public static byte[] wavTom4aBytes(File source){
        return null;
    }


    public static void main(String[] args) throws IOException {
//        File source = new File("src/main/java/com/graduation/utils/tmp/test.m4a");
//        byte[] wavBytes = m4aToWavBytes(source);
//        FileOutputStream fos = new FileOutputStream("src/main/java/com/graduation/utils/tmp/天净沙秋思.wav");
//        fos.write(wavBytes);
        Encoder encoder = new Encoder();
        try {
            for (int i = 0; i < encoder.getAudioEncoders().length; i++) {
                System.out.println(encoder.getAudioEncoders()[i].toString());
            }
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
