package com.springboot.freedesign.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static com.springboot.freedesign.common.FreeDesignConstants.ADMIN_HOME_URL;
import static com.springboot.freedesign.common.FreeDesignConstants.ARTWORKS;
import static com.springboot.freedesign.common.FreeDesignConstants.ROLE_ADMIN;
import static com.springboot.freedesign.common.FreeDesignConstants.ROLE_USER;


public class FreeDesignAuthenticationSuccessFilter implements AuthenticationSuccessHandler
{
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication)
			throws IOException
	{
		final String redirectURL = hasRole(authentication, ROLE_ADMIN) ? ADMIN_HOME_URL :
				hasRole(authentication, ROLE_USER) ? ARTWORKS : request.getContextPath();

		response.sendRedirect(redirectURL);
	}

	private boolean hasRole(final Authentication authentication, final String roleName)
	{
		return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(roleName));
	}
}