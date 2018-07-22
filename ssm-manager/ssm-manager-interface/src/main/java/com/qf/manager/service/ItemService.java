package com.qf.manager.service;

import com.qf.manager.pojo.dto.ItemQuery;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;

import java.util.List;

/**
 * @author it_mck 2018/7/20 19:08
 * <p>
 * version 1.0
 */
public interface ItemService {
    /**
     *
     * 查询
     *
     */

    ItemResult<TbItemCustom> listItems(PageParam pageparam,ItemQuery itemQuery);

    /**
     *
     * 根据前台传入的id删除商品
     */
    int updateItemByIds(List<Long> ids);
}
