package com.qf.manager.pojo.dto;

/**
 * 封装layui的入参信息
 *
 * @author it_mck 2018/7/20 17:35
 */
public class PageParam {


    private int page;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    //只要使用者给page和limit赋值,那么offset就自动计算出值来
    public int getOffset() {
        return (page - 1) * limit;
    }


}
