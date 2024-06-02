package com.springboot.freedesign.dao;

import com.springboot.freedesign.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDAO extends JpaRepository<User, Integer>
{
	List<User> findByUsername(final String username);
}
