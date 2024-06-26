package com.springboot.freedesign.security;

import com.springboot.freedesign.dao.UserDAO;
import com.springboot.freedesign.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		final User user = userDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

		return new MyUserDetails(user);
	}
}
