package com.qf.manager.service.impl;

import com.qf.manager.dao.TbItemCustomMapper;
import com.qf.manager.pojo.dto.ItemQuery;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.po.TbItem;
import com.qf.manager.pojo.po.TbItemExample;
import com.qf.manager.pojo.vo.TbItemCustom;
import com.qf.manager.pojo.vo.TbItemIndex;
import com.qf.manager.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * service层
 *
 * @author it_mck 2018/7/20 19:09
 * <p>
 * version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper tbItemCustomDao;
    //我在前边用TbItemCustomMapper继承了TbItemMapper,所以就不需要再次注入下边数据
//    @Autowired
//    private TbItemMapper tbItemMapper;

    @Autowired
    private SolrServer solrServer;

    /**
     * 查询商品
     *
     * @param pageparam
     * @return
     */
//    @Override
//    public ItemResult<TbItemCustom> listItems(PageParam pageparam,ItemQuery itemQuery) {
//
//        ItemResult<TbItemCustom> result = new ItemResult<>();
//        result.setCode(0);
//        result.setMsg("select success!!");
//        try {
//
//            //查询数量count
//            long count = tbItemCustomDao.countItems(itemQuery);
//            result.setCount(count);
//            //查询数据data
//            List<TbItemCustom> data = tbItemCustomDao.listItemsByPage(pageparam,itemQuery);
//            result.setData(data);
//
//        } catch (Exception e) {
//            result.setCode(-1);
//            result.setMsg("select failed");
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//
//        }
//
//        return result;
//    }

    /**
     * 查询商品
     * 将controller层产传来的数据,装入map集合中
     * 推荐使用map
     *
     * @param pageparam
     * @return
     */
    @Override
    public ItemResult<TbItemCustom> listItems(PageParam pageparam, ItemQuery itemQuery) {

        ItemResult<TbItemCustom> result = new ItemResult<>();
        result.setCode(0);
        result.setMsg("select success!!");
        try {

            Map<String, Object> map = new HashMap<>();
            map.put("pageparam", pageparam);
            map.put("itemQuery", itemQuery);
            //查询数量count
            long count = tbItemCustomDao.countItems(map);
            result.setCount(count);
            //查询数据data
            List<TbItemCustom> data = tbItemCustomDao.listItemsByPage(map);
            result.setData(data);

        } catch (Exception e) {
            result.setCode(-1);
            result.setMsg("select failed");
            logger.error(e.getMessage(), e);
            e.printStackTrace();

        }

        return result;
    }

    /**
     * 根据商品的id进行删除
     *
     * @param ids
     * @return
     */
    @Override
    public int updateItemByIds(List<Long> ids) {

        TbItem tbItem = new TbItem();
        TbItemExample example = null;
        int i = 0;
        try {
            tbItem.setStatus((byte) 3);
            example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            // i = tbItemMapper.updateByExampleSelective(tbItem, example);
            i = tbItemCustomDao.updateByExampleSelective(tbItem, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


        return i;
    }

    /**
     *
     * 为了导入索引库用来采集数据
     */

    @Override
    public void importIndexLib() {
        try {
            //1 采集数据
            List<TbItemIndex> list = tbItemCustomDao.listIndexByTwo();
            //2 导入索引库（遍历集合 documentList）
            for (TbItemIndex i : list) {
                //a Document
                SolrInputDocument document = new SolrInputDocument();
                //b 把list中每个对象的属性设置到document的field
                document.addField("id", i.getId());
                document.addField("item_title", i.getTitle());
                document.addField("item_sell_point", i.getSellPoint());
                document.addField("item_price", i.getPrice());
                document.addField("item_image", i.getImage());
                document.addField("item_category_name", i.getCatName());
                //c addDocument
                solrServer.add(document);
            }
            //d 提交
            solrServer.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


    }
}
