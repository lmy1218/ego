package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/16 18:20
 * @version V1.0
 */

import com.ego.manager.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @ClassName PicController
 * @Description 文件处理控制器
 * @date 2020/7/16 18:20
 **/
@Controller
public class PicController {

    @Autowired
    private PicService picServiceImpl;

    @RequestMapping("pic/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = picServiceImpl.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "上传图片失败");
        }
        return map;
    }

}
