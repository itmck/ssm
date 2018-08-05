package com.qf.manager.pojo.vo;

/**
 * 专门用于导入索引库的VO
 * User: DHC
 * Date: 2018/6/8
 * Time: 10:10
 * Version:V1.0
 */
public class TbItemIndex {
    private long id;
    private String title;
    private String sellPoint;
    private long price;
    private String image;
    private String catName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
