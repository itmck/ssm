package com.qf.portal.service.impl;

import com.qf.common.jedis.JedisClient;
import com.qf.common.util.JsonUtils;
import com.qf.common.util.StrKit;
import com.qf.portal.dao.TbContentMapper;
import com.qf.portal.pojo.po.TbContent;
import com.qf.portal.pojo.po.TbContentExample;
import com.qf.portal.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper tbContentDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> getContentListByCid(Long cid) {

        try {
            //1.去缓存中查
            String json = jedisClient.hget("contentList", cid + "");
            //判断json是否为空
            if (StrKit.notBlank(json)) {
                //json不为空
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        //2.去mysql里面查
        //2.1获取查询模板
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //2,2执行查询语句
        List<TbContent> contentList = tbContentDao.selectByExample(example);
        //2.3将查询结果存入缓存中
        try {
            jedisClient.hset("contentList", cid + "", JsonUtils.objectToJson(contentList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        //3存入缓存中
        return contentList;
    }
}
