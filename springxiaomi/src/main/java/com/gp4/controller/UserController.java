package com.gp4.controller;

import cn.dsna.util.images.ValidateCode;
import com.gp4.pojo.User;
import com.gp4.service.UserService;
import com.gp4.utils.Base64Utils;
import com.gp4.utils.RandomUtils;
import com.gp4.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ckd 2019/10/7 9:46
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        // 获取数据
        User user = new User();
        String repassword = request.getParameter("repassword");
        try {
            BeanUtils.populate(user,request.getParameterMap());
            if (StringUtils.isEmpty(user.getUsername())){
                request.setAttribute("registerMsg", "用户名不能为空");
                return "/register.jsp";
            }
            if (StringUtils.isEmpty(user.getPassword())){
                request.setAttribute("registerMsg", "密码不能为空");
                return "/register.jsp";
            }
            if (!repassword.equals(user.getPassword())){
                request.setAttribute("registerMsg", "两次密码不一致");
                return "/register.jsp";
            }
            if (StringUtils.isEmpty(user.getEmail())){
                request.setAttribute("registerMsg", "邮箱不能为空");
                return "/register.jsp";
            }

            // flag role code
            user.setFlag(0);
            user.setRole(1);
            user.setCode(RandomUtils.createActive());
            System.out.println(user.toString());
            userService.register(user);
            return "redirect:/registerSuccess.jsp";
        } catch (Exception e) {
            request.setAttribute("registerMsg", "注册失败");
            e.printStackTrace();
        }
        return "/register.jsp";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String auto = request.getParameter("auto");
        String valcode = request.getParameter("valcode");
        String servercode = (String) request.getSession().getAttribute("vcode");

        if (StringUtils.isEmpty(valcode)) {
            request.setAttribute("msg", "验证码不能为空");
            return "forward:/login.jsp";
        }
        if (!valcode.equalsIgnoreCase(servercode)) {
            request.setAttribute("msg", "验证码输入错误");
            return "forward:/login.jsp";
        }
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "forward:/login.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "forward:/login.jsp";
        }

        User user = userService.login(username, password);
        if (user == null) {
            request.setAttribute("msg", "用户名或密码错误");
            return "forward:/login.jsp";
        } else {
            if (user.getFlag() != 1) {
                request.setAttribute("msg", "用户尚未激活或被禁用");
                return "forward:/login.jsp";
            }
            if (user.getRole() != 1) {
                request.setAttribute("msg", "用户没有权限");
                return "forward:/login.jsp";
            }
            request.getSession().setAttribute("user", user);
            if (auto != null) {
                String userinfo = username + "#" + password;
                Cookie cookie = new Cookie("userinfo", Base64Utils.encode(userinfo));
                cookie.setMaxAge(60 * 60 * 24 * 14);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
            return "redirect:/index.jsp";

        }
    }

    @RequestMapping("code")
    public String code(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ValidateCode validateCode = new ValidateCode(100, 40, 4, 30);
        String code = validateCode.getCode();
        request.getSession().setAttribute("vcode",code);
        validateCode.write(response.getOutputStream());
        return null;
    }

    @RequestMapping("checkcode")
    public String checkCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String clientcode = request.getParameter("code");
        String servercode = (String) request.getSession().getAttribute("vcode");
        if (StringUtils.isEmpty(clientcode)){
            return null;
        }
        if (clientcode.equalsIgnoreCase(servercode)){
            response.getWriter().write("0");
        }else {
            response.getWriter().write("1");

        }
        return null;
    }

    @RequestMapping("logout")
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        Cookie cookie = new Cookie("userinfo", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/index.jsp";
    }

    @RequestMapping("checkusername")
    public String checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        if (username==null||username.trim().length()==0){
            return null;
        }

        User user = userService.checkUserName(username);
        if (user != null){
            response.getWriter().write("1");
        }else {
            response.getWriter().write("0");
        }
        return null;
    }
}