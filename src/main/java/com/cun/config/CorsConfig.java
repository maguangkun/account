package com.cun.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 实现基本的跨域请求
 * @author linhongcun
 *
 */
@Configuration
public class CorsConfig {
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
		corsConfiguration.addAllowedHeader("*"); // 允许任何头
		corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
		return new CorsFilter(source);
	}
//	@Bean
	public MyExceptionResolver error() {
		return new MyExceptionResolver();
	}
//	@Bean
	public SimpleMappingExceptionResolver cc(){
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties p = new Properties();
		p.setProperty("org.apache.shiro.authz.UnauthorizedException", "/cc");
		simpleMappingExceptionResolver.setExceptionMappings(p);
		return simpleMappingExceptionResolver;
	}
}
