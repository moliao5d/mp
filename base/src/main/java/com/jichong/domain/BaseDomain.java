package com.jichong.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

/**
 * Created on 2017/4/23.
 */
@Getter
@Setter
public abstract class BaseDomain {
    protected String _id;

    public abstract Document toMongoDoc();

}
