package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.LoginForm;
import com.cookabuy.entity.operation.dto.ResetPasswordForm;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.OperationUserRepository;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import com.cookabuy.spring.validator.CompoundValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.cookabuy.constant.ErrorConstant.*;

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

    @Autowired
    private DozerHelper dozerHelper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
    }

    @RequestMapping("/login")
    public Result dologin(@Validated LoginForm user, BindingResult bindingResult
            , Result result) {
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
            return new Result(ACCOUNT_NOT_EXIST);
        } catch (IncorrectCredentialsException e) {
            //// TODO: 2016/12/12 连续登录密码错误处理
            return new Result(PASSWORD_NOT_CORRECT);
        } catch (AuthenticationException e) {
            return new Result(LOGIN_FAIL);
        }
        log.info("登录成功");
        return new Result();
    }

    @RequestMapping("/reset_password")
    public Result resetPassword(@Valid ResetPasswordForm form, BindingResult bindingResult, Result result) {
        OperationUser loginUser = (OperationUser) SecurityUtils.getSubject().getPrincipal();
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("填写有误");
            result.setError(error);
            return result;
        }

        OperationUser user = operationUserRepository.findByUsername(loginUser.getUsername());

        String original = form.getOriginal();
        log.info("the original password is {}", original);

        //验证原密码是否正确
        if (!user.getPassword().equals(new Md5Hash(original).toString())) {
            log.info("original password is not match");
            result.setError(ORIGINAL_PASSWORD_NOT_MATCH);
            return result;
        }
        String hashedPassword = new Md5Hash(form.getConfirmPassword()).toString();
        //验证新密码是否与原密码相同
        if(user.getPassword().equals(hashedPassword)){
            log.info("new password is same with ");
            //todo  新密码与原密码相同时的处理
        }
        user.setPassword(hashedPassword);

        operationUserRepository.save(user);
        return result;
    }

}
