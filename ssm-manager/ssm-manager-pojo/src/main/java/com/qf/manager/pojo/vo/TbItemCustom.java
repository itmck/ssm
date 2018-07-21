package com.qf.manager.pojo.vo;

import com.qf.manager.pojo.po.TbItem;

/**
 * @author it_mck 2018/7/21 10:23
 * @ version 1.0
 */
public class TbItemCustom extends TbItem {

    /**
     *
     * 商品种类名称
     */
    private String catName;
    /**
     *
     * 商品状态
     */
    private String  statusName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
