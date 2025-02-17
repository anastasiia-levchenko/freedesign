package com.springboot.freedesign.mvconfig;

import com.springboot.freedesign.interceptors.LoggedInRedirectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginRedirectMvcConfig implements WebMvcConfigurer
{

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new LoggedInRedirectInterceptor());
	}
}
