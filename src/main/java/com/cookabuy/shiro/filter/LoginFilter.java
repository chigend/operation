package com.cookabuy.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.cookabuy.util.Result;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yejinbiao
 * @create 2016-12-21-11:13
 */
@Slf4j
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter in Filter:{}",this.getClass().getName());
        HttpServletRequest re  = (HttpServletRequest)request;
        if(re.getMethod().equals(HttpMethod.GET.name())){
            log.info("not authenticate yet,login first");
            Result result = new Result();
            response.setContentType(ContentType.APPLICATION_JSON.toString());
            result.setError("login first");
            @Cleanup PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(result));
        }else {
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
