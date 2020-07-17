package com.ego.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.manager.service
 * @date 2020/7/16 17:58
 */
public interface PicService {

    // 文件上传
    Map<String, Object> upload(MultipartFile file) throws IOException;
}
