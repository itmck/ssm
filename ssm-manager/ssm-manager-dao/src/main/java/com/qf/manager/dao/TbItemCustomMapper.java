package com.qf.manager.dao;

import com.qf.manager.pojo.vo.TbItemCustom;
import com.qf.manager.pojo.vo.TbItemIndex;

import java.util.List;
import java.util.Map;

/**
 * @author it_mck 2018/7/20 19:24
 * @ version 1.0
 */
public interface TbItemCustomMapper extends TbItemMapper{//继承目的为了后边可以使用其方法


    /**
     *
     *  查询数量count(未携带条件)
     * @return
     */
   // long countItems(@Param("itemQuery") ItemQuery itemQuery);

    /**
     * 方式2
     * @param map
     * @return
     */
    long countItems(Map<String,Object> map);

    /**
     *
     * 查询数据集合date(携带分页条件)
     *
     * 传入多个参数的解决方案
     * 第一:@param(来自于mybatis框架)
     * 第二,Map(在serviceImpl中直接拼装一个map.直接传递即可)
     *
     * @return
     */
   // List<TbItemCustom> listItemsByPage(@Param("pageparam") PageParam pageparam,@Param("itemQuery") ItemQuery itemQuery);

    /**
     *
     * 方式2
     * @param map
     * @return
     */
    List<TbItemCustom> listItemsByPage(Map<String,Object> map);

    /**
     * 为了导入索引库用来采集数据
     * @return
     */
    List<TbItemIndex> listIndexByTwo();
}
