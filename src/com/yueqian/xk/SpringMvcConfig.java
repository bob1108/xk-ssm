package com.yueqian.xk;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * springmvc.xml配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class))
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 添加控制器，如果没有指定，就设置默认访问源
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName("login");//其实就是访问：login.jsp
    }

    /**
     * 配置视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //定义一个内部资源视图解析器（InternalResourceViewResolver）
        registry.jsp("/",".jsp");//配置前后缀
    }
    /**
     * 配置默认的servlet访问，配置静态资源访问
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//放开静态资源
    }
}
