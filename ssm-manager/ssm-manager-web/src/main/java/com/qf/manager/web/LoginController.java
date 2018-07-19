package com.qf.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author it_mck 2018/7/18 22:54
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String  login(){

        return "login";

    }
}
