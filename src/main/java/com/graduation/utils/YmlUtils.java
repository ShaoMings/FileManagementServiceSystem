package com.graduation.utils;

import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/7/22 11:18
 * @since 1.0
 **/

public class YmlUtils {
    private static final String APPLICATION_NAME = "application.yml";

    private static Map result = new HashMap<>();

    /**

     * 根据文件名获取yml的文件内容

     * @return

     */

    public static Map getYmlByFileName(String file){
        result = new HashMap<>(20);

        if(file == null) {
            file = APPLICATION_NAME;
        }

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);

        Yaml props = new Yaml();

        Object obj = props.loadAs(in,Map.class);

        Map<String, Object> param = (Map) obj;


        
        for(Map.Entry<String, Object> entry:param.entrySet()){
            String key = entry.getKey();

            Object val = entry.getValue();

            if(val instanceof Map){
                forEachYaml(key,(Map) val);

            }else{
                result.put(key,val.toString());

            }

        }

        return result;

    }

    /**

     * 根据key获取值

     * @param key

     * @return

     */

    public static String getValue(String key){
        Map map = getYmlByFileName(null);

        if(map==null) {
            return null;
        }

        return (String) map.get(key);

    }

    /**

     * 遍历yml文件，获取map集合

     * @param key_str

     * @param obj

     * @return

     */

    public static Map forEachYaml(String key_str,Map<String, Object> obj){
        for(Map.Entry<String, Object> entry:obj.entrySet()){
            String key = entry.getKey();

            Object val = entry.getValue();

            String str_new = "";

            if(StringUtils.isNotEmpty(key_str)){
                str_new = key_str+ "."+key;

            }else{
                str_new = key;

            }

            if(val instanceof Map){
                forEachYaml(str_new,(Map) val);

            }else{
                result.put(str_new,val.toString());

            }

        }

        return result;

    }



    public static void main(String[] args) {
        System.out.println(getValue("spring.datasource.url"));

    }

}
