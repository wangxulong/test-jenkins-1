package com.example.testjenkins;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
 @Order(2)
 public class TestFilter extends OncePerRequestFilter  {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        System.out.println("first filter 1");
        chain.doFilter(request, response);
        System.out.println("first filter 2");
    }
}
