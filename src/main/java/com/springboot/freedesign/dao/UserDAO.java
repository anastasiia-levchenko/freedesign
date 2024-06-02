package com.springboot.freedesign.dao;

import com.springboot.freedesign.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDAO extends JpaRepository<User, Integer>
{
	Optional<User> findByUsername(final String username);
}
