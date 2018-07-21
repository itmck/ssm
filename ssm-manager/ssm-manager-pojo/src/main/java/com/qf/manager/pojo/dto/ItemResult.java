package com.qf.manager.pojo.dto;

import com.qf.manager.pojo.po.TbItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 详细信息参见layui的url返回值接口http://www.layui.com/demo/table/user/?page=1&limit=30
 *
 * @author it_mck 2018/7/20 17:45
 *
 * 封装layui的接口返回信息
 */
public class ItemResult<T> {

    private int code;
    private String msg;
    private long count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
