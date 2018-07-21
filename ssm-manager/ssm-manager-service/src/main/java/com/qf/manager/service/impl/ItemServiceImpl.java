package com.qf.manager.service.impl;

import com.qf.manager.dao.TbItemCustomMapper;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;
import com.qf.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * service层
 * @author it_mck 2018/7/20 19:09
 *
 * version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper tbItemCustomDao;

    @Override
    public ItemResult<TbItemCustom> listItems(PageParam pageparam) {

        ItemResult<TbItemCustom> result = new ItemResult<>();
        result.setCode(0);
        result.setMsg("select success!!");
        try {

            //查询数量count
            long count = tbItemCustomDao.countItems();
            result.setCount(count);
            //查询数据data
            List<TbItemCustom> data = tbItemCustomDao.listItemsByPage(pageparam);
            result.setData(data);

        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("select failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();

        }

        return result;
    }
}
