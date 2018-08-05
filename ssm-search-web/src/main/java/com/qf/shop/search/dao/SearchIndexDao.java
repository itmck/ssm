package com.qf.shop.search.dao;

import com.qf.shop.search.pojo.dto.TbItemIndexResult;
import com.qf.shop.search.pojo.vo.TbItemIndex;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchIndexDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SolrServer solrServer;


    public TbItemIndexResult search(SolrQuery query, int pageSize) {
        //recordCount 总记录数，pageSize 每页显示的条数
        //totalPages
        //list
        TbItemIndexResult result = new TbItemIndexResult();
        try {
            //创建索引库 List --DocumentList
            //搜索索引库DocumentList--List
            //获取查询响应
            QueryResponse response = solrServer.query(query);
            //文档域列表
            SolrDocumentList documentList = response.getResults();
            //获取符合条件总记录数
            long recordCount = documentList.getNumFound();
            result.setRecordCount(recordCount);
            //获取总页数
            long totalPages = (recordCount + pageSize - 1) / pageSize;
            result.setTotalPages(totalPages);
            //高亮集合
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //DocumentList--List
            List<TbItemIndex> list = new ArrayList<TbItemIndex>();
            for (SolrDocument document : documentList){
                //创建VO对象
                TbItemIndex item = new TbItemIndex();
                //把document里面的字段设值到VO对象中
                item.setId((String) document.get("id"));
                item.setSellPoint((String) document.get("item_sell_point"));
                item.setCatName((String) document.get("item_category_name"));
                item.setPrice((Long) document.get("item_price"));
                item.setImage((String) document.get("item_image"));
                //遍历高亮部分
                List<String> stringList = highlighting.get(document.get("id")).get("item_title");
                String title = "";
                if(stringList != null && ! stringList.isEmpty()){
                    title = stringList.get(0);
                }else {
                    title = (String)document.get("item_title");
                }
                item.setTitle(title);
                list.add(item);
            }
            result.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
