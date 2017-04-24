package com.jichong.dao;

import com.jichong.domain.BaseDomain;
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
public abstract class BaseDAO {

    protected abstract MongoCollection<Document> getColl();

    public  String save(BaseDomain domain){
        String _id = new ObjectId().toString();
        domain.set_id(_id);
        Document dd = domain.toMongoDoc();
        System.out.println(dd);
        getColl().insertOne(domain.toMongoDoc());
        return _id;
    }

    public  void update(String _id,BaseDomain newDomain){
        Document doc=new Document("$set",newDomain.toMongoDoc());
        getColl().updateOne(Filters.eq("_id",_id),doc);
    }
    public void update(Bson filter,Bson updates){
        getColl().updateOne(filter,updates);
    }

    public void delete(){

    }

    protected void actDel(){

    }

    protected List list(){
        List list=new ArrayList();
         list = getColl().find().into(list);
        return list;
    }

}
