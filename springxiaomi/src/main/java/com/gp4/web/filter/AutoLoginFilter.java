package com.gp4.web.filter;

import com.gp4.pojo.User;
import com.gp4.service.UserService;
import com.gp4.utils.Base64Utils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ckd 2019/9/11 9:45
 */
@WebFilter(filterName = "AutoLoginFilter",value = "/index.jsp")
public class AutoLoginFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 1判断当前是否已经登录
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userinfo")){
                    String value = cookie.getValue();
                    String userinfo = Base64Utils.decode(value);
                    String[] userinfos = userinfo.split("#");
                    UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userService");
                    User user2 = userService.login(userinfos[0], userinfos[1]);
                    if (user2 != null){
                        if (user2.getFlag()==1){
                            request.getSession().setAttribute("user", user2);
                            chain.doFilter(req, resp);
                            return;
                        }
                    }else {
                        Cookie cookie1 = new Cookie("userinfo", "");
                        cookie1.setMaxAge(0);
                        cookie1.setPath("/");
                        response.addCookie(cookie1);
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
