package cn.edu.hust.configuration;

import cn.edu.hust.interceptor.UserAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xiaolei03 on 16/12/5.
 */
@Configuration
public class UserAccessInterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册过滤器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new UserAccessInterceptor()).addPathPatterns("/home/**");
    }
}
