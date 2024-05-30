package com.springboot.freedesign.exceptions.exceptions;

public class ArtWorkNotFoundException extends RuntimeException
{
	public ArtWorkNotFoundException(final String message)
	{
		super(message);
	}
}
