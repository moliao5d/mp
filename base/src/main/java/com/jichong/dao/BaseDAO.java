package com.jichong.dao;

import com.jichong.domain.BaseDomain;
import com.jichong.util.Page;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/4/23.
 */
public abstract class BaseDAO implements IBaseDAO{

    public abstract MongoCollection<Document> getColl();

    public  String save(BaseDomain domain){
        String _id = new ObjectId().toString();
        domain.set_id(_id);
        getColl().insertOne(domain.toDoc());
        return _id;
    }

    public  void updateById(String _id,BaseDomain newDomain){
        Document doc=new Document("$set",newDomain.toDoc());
        getColl().updateOne(Filters.eq("_id",_id),doc);
    }

    public void update(Bson filter,Bson updates){
        getColl().updateOne(filter,updates);
    }

    public void deleteById(String _id){
        getColl().deleteOne(Filters.eq("_id",_id));

    }

    public Document getById(String _id){
        return getColl().find(Filters.eq("_id",_id)).first();
    }

    public Page getPage(Bson filter,int currentPage, int pageSize){
        List list=new ArrayList();
        Long count = getColl().count(filter);
        if(count>0){
            getColl().find(filter).skip((currentPage - 1) * pageSize).limit(pageSize).into(list);
        }
        return new Page(currentPage,pageSize,count.intValue(),list);
    }

}
