package com.springboot.freedesign.exceptions.exceptions;

public class UserHasNoPermissionToEdit extends RuntimeException
{
	public UserHasNoPermissionToEdit(final String message)
	{
		super(message);
	}
}
