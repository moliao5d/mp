package com.jichong.dao;

import com.jichong.entity.BaseDomain;
import com.jichong.util.Page;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Created by xl on 2017/4/24.
 */
public interface IBaseDAO {

    MongoCollection<Document> getColl();

    String save(BaseDomain domain);

    void updateById(String _id,BaseDomain newDomain);

    void update(Bson filter, Bson updates);

    void deleteById(String _id);

    Document getById(String _id);

    Page getPage(Bson filter, int currentPage, int pageSize);

}
