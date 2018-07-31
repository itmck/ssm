package com.qf.portal.service;

import com.qf.portal.pojo.po.TbContent;

import java.util.List;

/**
 * Create by it_mck 2018/7/31 12:34
 *
 * @Description:
 * @Version: 1.0
 */
public interface ContentService {

    /**
     * 通过分类进行内容查询
     * @param cid
     * @return
     */
    List<TbContent> getContentListByCid(Long cid);
}
