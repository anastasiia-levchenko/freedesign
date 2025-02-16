package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.dao.UserDAO;
import com.springboot.freedesign.models.User;
import com.springboot.freedesign.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUserById(final int id)
	{
		return userDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException("Could not find user by id"));
	}

	@Override
	public User findByUsername(final String username)
	{
		return userDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user by username"));
	}

}
