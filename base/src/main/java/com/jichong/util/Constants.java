package com.jichong.util;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created on 2017/4/23.
 */
@ToString
public class Constants {

    public static String MongoHost="";
    public static int MongoPort=0;
    public static String MongoUser="";
    public static String MongoPassword="";

    static {
        Properties p=new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class clz=Constants.class;
        Object obj=null;
        try {
            obj = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class fieldClass = field.getType();
            String fieldClassName = fieldClass.getSimpleName();
            try {
                if("Integer".equals(fieldClassName)||"int".equals(fieldClassName)){
                    Integer value = Integer.valueOf(p.getProperty(fieldName));
                    field.set(obj,value);
                }else {
                    field.set(obj,p.getProperty(fieldName));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}
