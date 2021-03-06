package com.ego.passport.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EgoResult;
import com.ego.common.utils.CookieUtils;
import com.ego.common.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Reference
    private TbUserDubboService tbUserDubboServiceImpl;

    @Resource
    private JedisDao jedisDaoImpl;

    /**
     * 登录逻辑
     * @param user
     * @param request
     * @param response
     * @return
     */
    @Override
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        EgoResult er = new EgoResult();

        TbUser userSelect = tbUserDubboServiceImpl.selByUser(user);
        if(userSelect != null){
            er.setStatus(200);
            String key = UUID.randomUUID().toString();
            jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
            jedisDaoImpl.expire(key, 60*60*24+7);

            CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60*60*24+7);
        }else{
            er.setMsg("用户名或密码错误");
        }
        return er;
    }

    /**
     * 获取登录信息
     * @param token
     * @return
     */
    @Override
    public EgoResult getUserInfoByToken(String token) {
        EgoResult er = new EgoResult();

        String json = jedisDaoImpl.get(token);
        if(json!=null && !json.equals("")){
            TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
            tbUser.setPassword(null);
            er.setStatus(200);
            er.setMsg("OK");
            er.setData(tbUser);
        }else{
            er.setMsg("获取失败");
        }
        return er;
    }

    /**
     * 退出登录
     * @param token
     * @param request
     * @param response
     * @return
     */
    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        EgoResult er = new EgoResult();
        Long del = jedisDaoImpl.del(token);
        CookieUtils.deleteCookie(request, response, "TT_TOKEN");
        er.setStatus(200);
        er.setMsg("OK");
        return er;
    }
}
