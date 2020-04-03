package com.yueqian.xk;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 完成了之前web.xml中的一些配置
 */
public class XkApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 用来配置ContextLoaderListener创建的应用的上下文的bean
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //Spring的配置
        return new Class[]{SpringConfig.class};
    }

    /**
     * 用于定义DespatcherServlet应用上下文中的bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //SpringMvc的配置
        return new Class[]{SpringMvcConfig.class};
    }

    /**
     * 返回映射到DispatcherServlet的请求路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
