package org.zhe.common.s_gateway.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.addAllowedOrigin("http://www.shop.gateway.org");
        corsConfiguration.addAllowedOrigin("http://www.shop.org"); //该方法是哪些域名可以获取权限来访问资源
        corsConfiguration.setAllowCredentials(true);             //是否允许产生cookie
        corsConfiguration.addAllowedMethod(HttpMethod.GET);     //允许什么方式的请求
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        corsConfiguration.addAllowedMethod(HttpMethod.HEAD);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedHeader("*");                 //允许什么样的头
        corsConfiguration.setMaxAge(3600L);                      //设置最大请求时长
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration); //放行什么路径
        return new CorsFilter(configurationSource);
    }

}
