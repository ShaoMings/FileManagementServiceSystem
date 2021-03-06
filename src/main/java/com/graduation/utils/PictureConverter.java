package com.graduation.utils;

import com.graduation.exception.FileConverterException;
import com.graduation.exception.ResponseException;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Description 常见图片格式转换工具类
 *
 * @author shaoming
 * @date 2021/8/20 09:56
 * @since 1.0
 */
public class PictureConverter {

    /**
     * 将传入的 png文件输入流转为jpg格式字节数组
     * @param file png文件输入流
     * @return 字节数组
     */
    public static byte[] pngToJpgBytes(InputStream file){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage bufferedImage = ImageIO.read(file);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image.createGraphics().drawImage(bufferedImage,0,0,Color.WHITE,null);
            ImageIO.write(image,"jpg",outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileConverterException("文件转换出错!");
    }


    /**
     * 将传入的 jpg文件输入流转为png格式字节数组
     * @param file jpg文件输入流
     * @return 字节数组
     */
    public static byte[] jpgToPngBytes(InputStream file){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIO.write(bufferedImage,"png",outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileConverterException("文件转换出错!");
    }


}
