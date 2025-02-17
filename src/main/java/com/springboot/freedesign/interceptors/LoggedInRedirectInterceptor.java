package com.springboot.freedesign.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import java.util.Objects;

import static com.springboot.freedesign.common.FreeDesignConstants.ADMIN_HOME_URL;
import static com.springboot.freedesign.common.FreeDesignConstants.ARTWORKS;
import static com.springboot.freedesign.common.FreeDesignConstants.LOGIN_URL;
import static com.springboot.freedesign.common.FreeDesignConstants.ROLE_ADMIN;
import static com.springboot.freedesign.common.FreeDesignConstants.ROLE_USER;


public class LoggedInRedirectInterceptor implements HandlerInterceptor
{
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
	{
		if (isOnLoginPage(request) && isAuthenticated())
		{
			fillInHttpResponse(request, response);
			return false;
		}
		return true;
	}

	private boolean isOnLoginPage(final HttpServletRequest request)
	{
		final UrlPathHelper urlPathHelper = new UrlPathHelper();

		return LOGIN_URL.equals(urlPathHelper.getLookupPathForRequest(request));
	}

	private void fillInHttpResponse(final HttpServletRequest request, final HttpServletResponse response)
	{
		final String encodedRedirectURL = response.encodeRedirectURL(request.getContextPath() + getIdentifiedUrlForUser());
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", encodedRedirectURL);
	}

	private String getIdentifiedUrlForUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return hasRole(authentication, ROLE_ADMIN) ? ADMIN_HOME_URL : hasRole(authentication, ROLE_USER) ? ARTWORKS : "";
	}

	private boolean hasRole(final Authentication authentication, final String roleName)
	{
		return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(roleName));
	}

	private boolean isAuthenticated()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (Objects.isNull(authentication) || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
		{
			return false;
		}
		return authentication.isAuthenticated();
	}
}