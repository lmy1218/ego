package com.ego.cart.interceptor;

import com.ego.common.pojo.EgoResult;
import com.ego.common.utils.CookieUtils;
import com.ego.common.utils.HttpClientUtil;
import com.ego.common.utils.JsonUtils;
import com.ego.pojo.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

   public final static ThreadLocal<EgoResult> threadLocal = new ThreadLocal<>();


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, "TT_TOKEN");
        if(token!=null && !token.equals("")){
            String json = HttpClientUtil.doPost("http://localhost:8084/user/token/"+token);
            EgoResult er = JsonUtils.jsonToPojo(json, EgoResult.class);
            if(er.getStatus() == 200){
                threadLocal.set(er);
                return true;
            }
        }
        String num = httpServletRequest.getParameter("num");

        httpServletResponse.sendRedirect("http://localhost:8084/user/showLogin?interurl=" + httpServletRequest.getRequestURL() +"%3Fnum=" + num);;
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
