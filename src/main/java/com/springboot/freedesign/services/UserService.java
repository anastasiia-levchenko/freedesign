package com.springboot.freedesign.services;

import com.springboot.freedesign.models.User;


public interface UserService
{
	User getUserById(int id);

	User findByUsername(final String username);

	int getCurrentSessionUserId();
}
