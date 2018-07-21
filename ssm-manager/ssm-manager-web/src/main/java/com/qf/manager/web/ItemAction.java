package com.qf.manager.web;

import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;
import com.qf.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author it_mck 2018/7/20 17:23
 */
@Controller
public class ItemAction {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ItemResult<TbItemCustom> listItems(PageParam pageparam) {

        ItemResult<TbItemCustom> result = null;

        try {
            result = itemService.listItems(pageparam);
        } catch (Exception e) {

            e.printStackTrace();

        }

        return result;
    }

}
