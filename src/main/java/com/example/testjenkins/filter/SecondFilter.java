package com.example.testjenkins.filter;

import com.example.testjenkins.config.AppConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
//@Configuration
@Order(1)
@Data
public class SecondFilter extends OncePerRequestFilter {

    @Autowired
    AppConfig appConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        System.out.println("second filter 1" + appConfig.getName());
        chain.doFilter(request, response);
        System.out.println("second filter 2");

    }
}
