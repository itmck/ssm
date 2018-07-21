package com.qf.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author it_mck 2018/7/19 19:11
 */
@Controller
public class ManagerIndexAction {


    /**
     *
     * 一层目录下跳转
     *
     */
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable String  page){

        return page;
    }
    /**
     *
     * 二层目录下跳转
     *
     */
    @RequestMapping(value = "/pages/{param1}",method = RequestMethod.GET)
    public  String index2(@PathVariable String param1){

        return  "pages/"+param1;
    }

    /**
     *
     * 三层目录下跳转
     *
     */
    @RequestMapping(value = "/pages/{param1}/{param2}",method = RequestMethod.GET)
    public  String index3(@PathVariable String param1,@PathVariable String param2){

        return  "pages/"+param1+"/"+param2;
    }
}
