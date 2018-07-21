package com.qf.manager.dao;

import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;

import java.util.List;

/**
 * @author it_mck 2018/7/20 19:24
 * @ version 1.0
 */
public interface TbItemCustomMapper extends TbItemMapper{


    /**
     *
     *  查询数量count(未携带条件)
     * @return
     */
    long countItems();

    /**
     *
     * 查询数据集合date(携带分页条件)
     *
     * @param pageparam
     * @return
     */
    List<TbItemCustom> listItemsByPage(PageParam pageparam);

}
