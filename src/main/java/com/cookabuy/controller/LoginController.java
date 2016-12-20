package com.cookabuy.controller;

import com.cookabuy.constant.View;
import com.cookabuy.entity.operation.dto.LoginForm;
import com.cookabuy.util.Result;
import com.cookabuy.validator.LoginFormValidator;
import com.cookabuy.validator.UserAddFormValidator;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 2016/12/9
 */
@RestController
@Slf4j
public class LoginController {


    @InitBinder("loginForm")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new LoginFormValidator());
    }
    @RequestMapping("/login")
    public Result dologin(@Valid LoginForm user,
                          HttpSession session, HttpServletRequest request) {
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
            return new Result("账户名不存在");
        } catch (IncorrectCredentialsException e) {
            //// TODO: 2016/12/12 连续登录密码错误处理
            return new Result("账户名或密码错误");
        } catch (AuthenticationException e) {
            return new Result("登录失败,请稍后重试");
        }
        log.info("登录成功");
        return new Result();
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
