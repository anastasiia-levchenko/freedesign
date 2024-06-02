package com.springboot.freedesign.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig
{
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());

		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authenticationProvider(authenticationProvider());

		httpSecurity.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/").hasRole("USER")
						.anyRequest().authenticated()).formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
				.logout(LogoutConfigurer::permitAll)
				.exceptionHandling(eh -> eh.accessDeniedPage("/403"))
				.formLogin(formLogin -> formLogin.defaultSuccessUrl("/artworks", true));

		return httpSecurity.build();
	}
}
