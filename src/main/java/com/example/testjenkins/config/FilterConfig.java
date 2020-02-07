package com.example.testjenkins.config;

import com.example.testjenkins.TestFilter;
import com.example.testjenkins.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean firstFilter(){
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new TestFilter());
//        registration.addUrlPatterns("/**");
//        registration.setName("firstFilter");
//        registration.setOrder(1);
//        return registration;
//    }

    @Bean
    public TestFilter testFilter(){
        return  new TestFilter();
    }

    @Bean
    public SecondFilter secondFilter(){
        return  new SecondFilter();
    }
}
