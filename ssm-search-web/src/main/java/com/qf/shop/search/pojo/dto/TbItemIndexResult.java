package com.qf.shop.search.pojo.dto;

import com.qf.shop.search.pojo.vo.TbItemIndex;

import java.util.List;


public class TbItemIndexResult {
    //符合条件的总记录数
    private long recordCount;
    //总页数
    private long totalPages;
    //指定分页的集合
    private List<TbItemIndex> list;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbItemIndex> getList() {
        return list;
    }

    public void setList(List<TbItemIndex> list) {
        this.list = list;
    }
}
