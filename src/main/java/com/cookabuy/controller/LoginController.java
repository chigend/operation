package com.cookabuy.controller;

import com.cookabuy.constant.View;
import com.cookabuy.entity.operation.dto.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 2016/12/9
 */
@Controller
@Slf4j
public class LoginController {


    @RequestMapping("/login")
    public String dologin(LoginForm user, Model model,
                          HttpSession session, HttpServletRequest request) {
        if (request.getMethod().equals(HttpMethod.GET.name())) {
            return "login";
        }
        log.info("the login form :{}", user);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if (subject.isAuthenticated()) {
            log.info("使用另一个账号进行登录,登出第一个用户");
            subject.logout();
        }
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("error", "账户名不存在");
            return View.LOGIN;
        } catch (IncorrectCredentialsException e) {
            //// TODO: 2016/12/12 连续登录密码错误处理
            model.addAttribute("error","账户名或密码错误");
            return View.LOGIN;
        } catch (AuthenticationException e) {
            model.addAttribute("error", "登录失败，稍后重试");
            return View.LOGIN;
        }
        log.info("登录成功");
        model.addAttribute("hello", "hello world");
        return "redirect:hello";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
