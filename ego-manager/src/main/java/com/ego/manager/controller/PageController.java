package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/12 19:55
 * @version V1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @ClassName PageController
 * @Description 首页展示
 * @date 2020/7/12 19:55
 **/
@Controller
public class PageController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("{page}")
    public String showPage(@PathVariable("page") String page) {
        return page;
    }


}
