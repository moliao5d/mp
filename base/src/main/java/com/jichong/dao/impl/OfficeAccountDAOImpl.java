package com.jichong.dao.impl;

import com.jichong.dao.BaseDAO;
import com.jichong.dao.IOfficeAccountDAO;
import com.jichong.util.DBUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Created on 2017/4/23.
 */
public class OfficeAccountDAOImpl extends BaseDAO implements IOfficeAccountDAO {

    @Override
    protected MongoCollection<Document> getColl() {
        return DBUtil.getColl("gh");
    }


//    public void save(OfficeAccount officeAccount) {
//
//    }



}
