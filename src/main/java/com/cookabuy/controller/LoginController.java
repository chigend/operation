package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.LoginForm;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 2016/12/9
 */
@Controller
@Slf4j
public class LoginController {
    @RequestMapping("/login")

    public String dologin(@Valid LoginForm user, BindingResult error, Model model,
                          HttpSession session,HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (request.getMethod().equals(HttpMethod.GET.name())) {
            return "login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if (subject.isAuthenticated()) {
            log.info("使用另一个账号进行登录,登出第一个用户");
            subject.logout();
        }
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("error","账户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //// TODO: 2016/12/12 连续登录密码错误处理
//            Integer login_times = (Integer) session.getAttribute(UserConstant.LOGIN_TIMES);
//            if (login_times == null) {
//                session.setAttribute(UserConstant.LOGIN_TIMES, 1);
//            } else {
//                login_times++;
//                logger.info("登录失败次数为{}", login_times);
//                session.setAttribute(UserConstant.LOGIN_TIMES, login_times);
//
//            }
//            result.setError("账号或密码错误");
//            return result;
        } catch (AuthenticationException e) {
            model.addAttribute("error","登录失败，稍后重试");
        }
        session.setAttribute("realName",((OperationUser)subject.getPrincipal()).getUsername());
        return "hello";
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
