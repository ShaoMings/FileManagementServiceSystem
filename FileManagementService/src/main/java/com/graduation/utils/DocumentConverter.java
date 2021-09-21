package com.graduation.utils;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import com.graduation.exception.FileConverterException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * Description 文档转换器  用于将常见格式文件转为PDF文件
 *
 * @author shaoming
 * @date 2021/8/27 14:27
 * @since 1.0
 */
public class DocumentConverter {

    private static final String OUTPUT_FILE_PATH = Constant.OUTPUT_TMP_FILE_PATH+"tmp.pdf";

    /**
     *  删除生成的中间文件
     * @param source 源文件
     * @param target 目标文件
     */
    public static void deleteFile(File source,File target){
        if (source.exists()) {
            source.delete();
        }
        assert target != null;
        if (target.exists()) {
            target.delete();
        }
    }


    /**
     * 将txt文件转为pdf文件字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] txtToPdfBytes(File source){
        File target = null;
        try {
            String content = FileUtils.readFileToString(source, "utf-8");
            Document doc = new Document();
            DocumentBuilder documentBuilder = new DocumentBuilder(doc);
            documentBuilder.writeln(content);
            doc.save(OUTPUT_FILE_PATH, SaveFormat.PDF);
           target = new File(OUTPUT_FILE_PATH);
            return AudioConverter.getBytesFromFile(target);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           deleteFile(source,target);
        }
        throw new FileConverterException("txt转pdf失败!");
    }

    /**
     * 将word文档转为pdf字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] wordToPdfBytes(File source){
        File target = null;
        try {
            Document doc = new Document(source.getAbsolutePath());
            doc.save(OUTPUT_FILE_PATH, SaveFormat.PDF);
            target = new File(OUTPUT_FILE_PATH);
            return AudioConverter.getBytesFromFile(target);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            deleteFile(source,target);
        }
        throw new FileConverterException("word转pdf失败!");
    }

    /**
     * 将ppt文件转pdf字节数组
     * @param source 源文件
     * @return 字节数组
     */
    public static byte[] pptToPdfBytes(File source){
        File target = null;
        try {
            Presentation presentation = new Presentation(new FileInputStream(source));
            presentation.save(OUTPUT_FILE_PATH, com.aspose.slides.SaveFormat.Pdf);
            target = new File(OUTPUT_FILE_PATH);
            return AudioConverter.getBytesFromFile(target);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            deleteFile(source,target);
        }
        throw new FileConverterException("ppt转pdf失败!");
    }


    public static byte[] excelToPdfBytes(File source){
        File target = null;
        try {
            Workbook workbook = new Workbook(new FileInputStream(source));
            workbook.save(OUTPUT_FILE_PATH, com.aspose.cells.SaveFormat.PDF);
            target = new File(OUTPUT_FILE_PATH);
            return AudioConverter.getBytesFromFile(target);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            deleteFile(source,target);
        }
        throw new FileConverterException("excel转pdf失败!");
    }

    public static byte[] autoConvertByFileType(File source,String suffix){
        if (StringUtils.isBlank(suffix) || source == null){
            throw new FileConverterException("文档转pdf失败!");
        }
        String[] powerpointTypes = {"ppt","pptx"};
        String[] docTypes = {"docx","doc"};
        String[] excelTypes = {"xlsx","xls"};
        if (Arrays.asList(powerpointTypes).contains(suffix)){
            return pptToPdfBytes(source);
        }else if (Arrays.asList(docTypes).contains(suffix)){
            return wordToPdfBytes(source);
        }else if (Arrays.asList(excelTypes).contains(suffix)){
            return excelToPdfBytes(source);
        }else if ("txt".equals(suffix)){
            return txtToPdfBytes(source);
        }else {
            throw new FileConverterException("目前文档格式不支持!");
        }
    }

    public static void main(String[] args) {
        File source = new File(Constant.OUTPUT_TMP_FILE_PATH+"test.xlsx");
        byte[] bytes = excelToPdfBytes(source);
    }
}
