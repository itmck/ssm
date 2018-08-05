package com.qf.portal.web;

import com.qf.common.util.PropKit;
import com.qf.portal.pojo.po.TbContent;
import com.qf.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by it_mck 2018/7/31 17:34
 *
 * @Description:实现轮播图的效果
 * @Version: 1.0
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest Request){

        Long cid = PropKit.use("index.properties").getLong("lunbo.cid");

        List<TbContent> clist = null;
        try {
            clist = contentService.getContentListByCid(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request.setAttribute("clist",clist);

        return "index";
    }



}
