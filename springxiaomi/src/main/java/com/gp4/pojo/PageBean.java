package com.gp4.pojo;

import java.util.List;

/**
 * ckd 2019/9/11 11:18
 */
public class PageBean <T>{
    private int pageNum;
    private int pageSize;
    private long totalSize;
    private int pageCount;
    private List<T> data;
    private int startPage;
    private int endPage;


    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        this.pageCount = (int) (totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1);
        this.startPage=pageNum-4;
        this.endPage=pageNum+5;
        if (pageNum<5){
            this.startPage=1;
            this.startPage=10;
        }
        if (pageCount-pageNum<5){
            this.startPage=pageCount-9;
            this.endPage=pageCount;
        }
        if (pageCount<10){
            this.startPage=1;
            this.endPage=pageCount;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
