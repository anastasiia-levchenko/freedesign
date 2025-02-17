package com.springboot.freedesign.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.springboot.freedesign.common.FreeDesignConstants.LOGIN_URL;


@Configuration
@EnableWebSecurity
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

		httpSecurity.authorizeHttpRequests(
						auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN")
								.requestMatchers("/artworks/**").hasRole("USER").requestMatchers("/images/**").permitAll()
								.anyRequest()
								.authenticated()).formLogin((form -> form.successHandler(customSuccessHandler()))).formLogin(form -> form
						.loginPage(LOGIN_URL)
						.permitAll())
				.logout(LogoutConfigurer::permitAll)
				.exceptionHandling((exception) -> exception.accessDeniedPage("/error/accessDenied"));
		;
		return httpSecurity.build();
	}

	private AuthenticationSuccessHandler customSuccessHandler()
	{
		return new FreeDesignAuthenticationSuccessFilter();
	}
}
