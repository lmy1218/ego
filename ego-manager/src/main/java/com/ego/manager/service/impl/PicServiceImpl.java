package com.ego.manager.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.manager.service.impl
 * @author Administrator
 * @date 2020/7/16 17:59
 * @version V1.0
 */

import com.ego.common.utils.FtpUtil;
import com.ego.common.utils.IDUtils;
import com.ego.manager.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @ClassName PicServiceImpl
 * @Description 文件上传业务层
 * @date 2020/7/16 17:59
 **/
@Service
public class PicServiceImpl implements PicService {

    // 主机地址
    @Value("${ftpclient.host}")
    private String host;
    // 端口号
    @Value("${ftpclient.port}")
    private int port;
    // 用户名
    @Value("${ftpclient.username}")
    private String username;
    // 密码
    @Value("${ftpclient.password}")
    private String password;
    // 图片服务器基本路径
    @Value("${ftpclient.basepath}")
    private String basePath;
    // 图片存放目录
    @Value("${ftpclient.filepath}")
    private String filePath;


    @Override
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        // 定义文件名
        String genImageName = IDUtils.genImageName() +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件
        boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, genImageName, file.getInputStream());
        // 判断是否上传成功
        Map<String, Object> map = new HashMap<>();
        if (result) {
            map.put("error", 0);
            map.put("url", "http://" + host + "/" + genImageName);
        } else {
            map.put("error", 1);
            map.put("message", "图片上传失败");
        }

        return map;
    }
}
