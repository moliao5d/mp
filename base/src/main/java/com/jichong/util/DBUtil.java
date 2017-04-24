package com.jichong.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

/**
 * Created on 2017/4/23.
 */
public enum  DBUtil {
    dbutil;
    private static MongoClient client=null;
    static {
        client=new MongoClient(Constants.MongoHost,Constants.MongoPort);
    }

    public static MongoCollection<Document> getColl(String tableName){
        return client.getDatabase("mp").getCollection(tableName);
    }

    public static MongoCollection<Document>  getColl(String dbName,String tableName){
        if(StringUtils.isEmpty(dbName)){
            return getColl(tableName);
        }
        return client.getDatabase(dbName).getCollection(tableName);
    }
}
