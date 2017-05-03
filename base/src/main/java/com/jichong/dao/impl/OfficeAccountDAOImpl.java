package com.jichong.dao.impl;

import com.jichong.dao.IOfficeAccountDAO;
import com.jichong.util.DBUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017/4/23.
 */
@Repository
public class OfficeAccountDAOImpl extends BaseDAOImpl implements IOfficeAccountDAO {

    public MongoCollection<Document> getColl() {
        return DBUtil.getColl("gh");
    }


}
