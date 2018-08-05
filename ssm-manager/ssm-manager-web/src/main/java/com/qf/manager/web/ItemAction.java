package com.qf.manager.web;

import com.qf.common.dto.MessageResult;
import com.qf.manager.pojo.dto.ItemQuery;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.vo.TbItemCustom;
import com.qf.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author it_mck 2018/7/20 17:23
 */
@Controller
public class ItemAction {

    @Autowired
    private ItemService itemService;

    /**
     * 查询
     *
     * @param pageparam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ItemResult<TbItemCustom> listItems(PageParam pageparam, ItemQuery itemQuery) {

        ItemResult<TbItemCustom> result = null;

        try {
            result = itemService.listItems(pageparam, itemQuery);
        } catch (Exception e) {

            e.printStackTrace();

        }

        return result;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/item/batch", method = RequestMethod.POST)
    public int removeItem(@RequestParam("ids[]") List<Long> ids) {

        //使用快捷键ctrl+alt+t 快速完成try..catch
        int i = 0;
        try {
            i = itemService.updateItemByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;

    }

    @ResponseBody
    @RequestMapping(value = "/item/indexlib/import", method = RequestMethod.POST)
    public MessageResult importIndexLib() {
        MessageResult mr = new MessageResult();
        mr.setSuccess(false);
        mr.setMsg("import failed");
        try {
            itemService.importIndexLib();
            mr.setSuccess(true);
            mr.setMsg("import success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mr;
    }
}
