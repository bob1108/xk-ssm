package com.yueqian.xk.controller;

import com.yueqian.xk.beans.UserInfo;
import com.yueqian.xk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userInfo:接收前台传过来的数据  前台通过<input name="xxx">绑定
     * @param model：传递数据到前台
     * @param session：session
     * @return
     */
    @RequestMapping("/login")//访问的地址 xk1/user/login
    public String login(UserInfo userInfo, Model model, HttpSession session){
        if(userInfo.getUsername().equals("") && userInfo.getPassword().equals("")){
            //账号不正确
            model.addAttribute("msg","账号或者密码不能为空！");
            return "login";//login.jsp
        }
        //根据用户名账户查询数据库

        UserInfo user = userService.login(userInfo);
        if(user==null){
            //账号不正确
            model.addAttribute("msg","登录失败！");
            return "login";//login.jsp
        }
        if(user.getPassword().equals(userInfo.getPassword())){
            System.out.println("----------------"+user.getUsername());
            System.out.println();
            //登录成功，将当前的用户信息保存到session中
            session.setAttribute("loginedUser",user);
            return "redirect:/main.jsp";//重定向到主界面
        }
        //密码不正确
        model.addAttribute("msg","账号或者密码不正确");
        return "login";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清理session
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
