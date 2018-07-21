package com.qf.manager.service;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;

/**
 * @author it_mck 2018/7/20 19:08
 * <p>
 * version 1.0
 */
public interface ItemService {
    //查询
    ItemResult<TbItemCustom> listItems(PageParam pageparam);
}
