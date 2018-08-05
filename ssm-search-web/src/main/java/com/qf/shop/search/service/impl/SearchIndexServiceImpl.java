package com.qf.shop.search.service.impl;

import com.qf.shop.search.dao.SearchIndexDao;
import com.qf.shop.search.pojo.dto.TbItemIndexResult;
import com.qf.shop.search.service.SearchIndexService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by it_mck 2018/8/2 19:58
 *
 * @Description:
 * @Version: 1.0
 */
@Service
public class SearchIndexServiceImpl implements SearchIndexService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SearchIndexDao searchIndexDao;

    @Override
    public TbItemIndexResult searchIndex(String keyword, int pageIndex, int pageSize) {

        TbItemIndexResult result = null;

        try {

            //创建solr查询对象
            SolrQuery query = new SolrQuery();
            //设置查询的关键字
            query.setQuery(keyword);
            if(pageIndex<=0){
                pageIndex=1;
            }
            //设置查询的分页条件
            query.setStart((pageIndex - 1) * pageSize);
            query.setRows(pageSize);
            //设置默认查询字段
            query.set("df", "item_keywords");
            //设置高亮部分
            query.setHighlight(true);
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<em style='color:red;'>");
            query.setHighlightSimplePost("</em>");
            //调用DAO层方法进行查询，查询条件：query
            result = searchIndexDao.search(query,pageSize);


        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return result;
    }


}
