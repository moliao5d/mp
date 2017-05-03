package com.jichong.dao.impl;

import com.jichong.dao.IWXUserDAO;
import com.jichong.util.DBUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

/**
 * Created by xl on 2017/5/3.
 */
@Repository
public class WXUserDAOImpl extends BaseDAOImpl implements IWXUserDAO{

    @Override
    public MongoCollection<Document> getColl() {
        return DBUtil.getColl("wx_user");
    }
}
