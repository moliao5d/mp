package com.jichong.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

/**
 * Created on 2017/4/23.
 */
@Getter
@Setter
public class OfficeAccount extends BaseDomain{

    private String original_id;
    private String token;
    private String appid;
    private String appSecret;
    private String aesKey;
    private int status;


    @Override
    public Document toMongoDoc() {
        Document doc=new Document();
        doc.append("_id",_id);
        return doc;
    }
}
