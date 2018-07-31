package com.qf.portal.service.impl;

import com.qf.common.jedis.JedisClient;
import com.qf.portal.dao.TbContentMapper;
import com.qf.portal.pojo.po.TbContent;
import com.qf.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by it_mck 2018/7/31 12:38
 *
 * @Description:
 * @Version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> getContentListByCid(Long cid) {

        //1.去缓存中查
        //2.去mysql里面查
        //3存入缓存中
        return null;
    }
}
