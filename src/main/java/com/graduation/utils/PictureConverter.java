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
     *  将传入的svg文件输入流转为png格式字节数组
     * @param file  svg文件输入流
     * @return 字节数组
     */
    public static byte[] svgToPngBytes(FileInputStream file) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderInput input = new TranscoderInput(file);
        TranscoderOutput output = new TranscoderOutput(outputStream);
        Transcoder transcoder = new PNGTranscoder();
        try {
            transcoder.transcode(input, output);
        } catch (TranscoderException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    /**
     *  将传入的 emf文件输入流转为png格式字节数组
     * @param file   emf文件输入流
     * @return 字节数组
     */
    public static byte[] emfToPngBytes(FileInputStream file){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EMFInputStream emfInputStream = new EMFInputStream(file, EMFInputStream.DEFAULT_VERSION);
        try {
            EMFRenderer emfRenderer = new EMFRenderer(emfInputStream);
            final int width = (int) emfInputStream.readHeader().getBounds().getWidth();
            final int height = (int) emfInputStream.readHeader().getBounds().getHeight();
            final BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = res.createGraphics();
            emfRenderer.paint(graphics);
            ImageIO.write(res,"png",outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileConverterException("文件转换出错!");
    }

    /**
     *  将传入的 svg文件输入流转为jpg格式字节数组
     * @param file svg文件输入流
     * @return 字节数组
     */
    public static byte[] svgToJpgBytes(FileInputStream file){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderInput input = new TranscoderInput(file);
        TranscoderOutput output = new TranscoderOutput(outputStream);
        try {
            Transcoder transcoder = new JPEGTranscoder();
            transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,0.99f);
            transcoder.transcode(input, output);
            return outputStream.toByteArray();
        } catch (TranscoderException e) {
            e.printStackTrace();
        }
        throw new FileConverterException("文件转换出错!");
    }

    /**
     * 将传入的 png文件输入流转为jpg格式字节数组
     * @param file png文件输入流
     * @return 字节数组
     */
    public static byte[] pngToJpgBytes(FileInputStream file){
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
    public static byte[] jpgToPngBytes(FileInputStream file){
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
