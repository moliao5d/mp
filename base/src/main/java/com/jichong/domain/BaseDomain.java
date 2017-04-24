package com.jichong.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created on 2017/4/23.
 */
@Getter
@Setter
public abstract class BaseDomain {
    protected String _id;

    public Document toDoc() {
        return toDoc(true);
    }

    public Document toDoc(boolean ignoreNull) {
        Document doc = new Document();
        Class<? extends BaseDomain> clz = this.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String propertyName = pd.getName();
                Method readMethod = pd.getReadMethod();
                Object value = readMethod.invoke(this);

                if (value != null) {
                    if (value instanceof String || value instanceof Long || value instanceof Integer || value instanceof Byte || value instanceof Short || value instanceof Double || value instanceof Float || value instanceof Character || value instanceof Boolean || value instanceof Date) {
                        //支持的数据类型
                    } else if (value instanceof BaseDomain) {        //value 值还是domain,再转
                        value = ((BaseDomain) value).toDoc();
                    } else {
                        value = value.toString();
                    }
                    doc.put(propertyName, value);
                } else {  //value=null
                    if (!ignoreNull) {
                        doc.put(propertyName, null);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public BaseDomain doc2Domain(Document doc) {
        Class<? extends BaseDomain> clz = this.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            Set<Map.Entry<String, Object>> entries = doc.entrySet();
            ArrayList<String> propertyNames = new ArrayList<>();
            Map<String,Method> methodMap = new HashMap();
            Map<String,Class> typeMap = new HashMap();
            for (PropertyDescriptor pd : pds) {
                String propertyName = pd.getName();
                propertyNames.add(propertyName);
                Method writeMethod = pd.getWriteMethod();
                Class<?> propertyType = pd.getPropertyType();
                methodMap.put(propertyName,writeMethod);
                typeMap.put(propertyName,propertyType);
            }
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                if (propertyNames.contains(key) && entry.getValue() != null) {
                    Object value=entry.getValue();
                    if(typeMap.get(key).getSuperclass()==BaseDomain.class){
                        Document doc1 = (Document) entry.getValue();
                        Class<BaseDomain> clz1 = typeMap.get(key);
                        BaseDomain domain1 = clz1.newInstance();
                        value=domain1.doc2Domain(doc1);
                    }
                    Method writeMethod = methodMap.get(key);
                    writeMethod.invoke(this,value);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
