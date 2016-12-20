package com.cookabuy.controller;

import com.cookabuy.constant.View;
import com.cookabuy.entity.operation.dto.LoginForm;
import com.cookabuy.entity.operation.dto.ResetPasswordForm;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.OperationUserRepository;
import com.cookabuy.util.Result;
import com.cookabuy.validator.CompoundValidator;
import com.cookabuy.validator.LoginFormValidator;
import com.cookabuy.validator.UserAddFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 2016/12/9
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private CompoundValidator validator;

    @Autowired
    private OperationUserRepository operationUserRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(this.validator);
    }

    @RequestMapping("/login")
    public Result dologin(@Validated LoginForm user,BindingResult bindingResult
                         ,Result result) {
        log.info("the login form :{}", user);
        Subject subject = SecurityUtils.getSubject();
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("填写有误");
            result.setError(error);
            return result;
        }

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

    @RequestMapping("/reset_password")
    public Result resetPassword(@Validated ResetPasswordForm form, BindingResult bindingResult, Result result){
            if (bindingResult.hasErrors()) {
                String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("填写有误");
                result.setError(error);
                return result;
            }

            OperationUser user = operationUserRepository.findByUsername(form.getUsername());

            if (user == null) {
                result.setError("该用户不存在");
                return result;
            }
            String original = form.getOriginal();
            log.info("the original password is {}",original);
            if (!user.getPassword().equals(new Md5Hash(original).toString())) {
                log.info("original password is not match");
                result.setError("原密码不正确");
                return result;
            }
            String hashedPassword = new Md5Hash(form.getConfirmPassword()).toString();
            user.setPassword(hashedPassword);
            operationUserRepository.save(user);
            return result;
        }

    }
