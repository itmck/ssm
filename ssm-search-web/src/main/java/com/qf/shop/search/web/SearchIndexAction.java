package com.qf.shop.search.web;

import com.qf.common.util.PropKit;
import com.qf.common.util.StrKit;
import com.qf.shop.search.pojo.dto.TbItemIndexResult;
import com.qf.shop.search.service.SearchIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create by it_mck 2018/8/2 19:32
 *
 * @Description:
 * @Version: 1.0
 */
@Controller
public class SearchIndexAction {

    @Autowired
    private SearchIndexService searchIndexService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){

        return "index";
    }

@RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(String keyword, @RequestParam(defaultValue = "1") int pageIndex, Model model){

        //pageIndex当前页的页码
        //pageSize 每页显示的条数
    if(StrKit.notBlank(keyword)) {
        System.out.println(keyword);
        int pageSize = PropKit.use("file.properties").getInt("search.pagesize");
        //服务层查询
        TbItemIndexResult result = searchIndexService.searchIndex(keyword, pageIndex, pageSize);
        //存放作用域中
        model.addAttribute("list", result.getList());
    }
    //转发到下一个页面

        return "index";
    }


}
