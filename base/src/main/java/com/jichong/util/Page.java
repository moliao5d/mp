package com.jichong.util;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.List;

/**
 * Created by xl on 2017/4/24.
 */
@Getter
@Setter
public class Page {

    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int prePage;
    private int nextPage;

    private List<Document> listData;

    public Page(int currentPage, int pageSize,int totalCount, List<Document> listData) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        this.prePage= currentPage <=1?1:currentPage-1;
        this.nextPage= currentPage>=totalPage?totalPage:currentPage+1;
        this.listData = listData;
    }
}
