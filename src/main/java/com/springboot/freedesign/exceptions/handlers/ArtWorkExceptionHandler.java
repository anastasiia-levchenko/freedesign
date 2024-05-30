package com.springboot.freedesign.exceptions.handlers;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.exceptions.exceptions.ArtWorksExportException;
import com.springboot.freedesign.exceptions.exceptions.NoExportStrategyFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
public class ArtWorkExceptionHandler
{

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> handleNoResourceFoundException(final NoResourceFoundException ex)
	{
		return new ResponseEntity<>(FreeDesignConstants.NO_RESOURCE_FOUND, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ArtWorkNotFoundException.class)
	public ResponseEntity<String> handleNoArtWorkFoundException(final ArtWorkNotFoundException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoExportStrategyFoundException.class)
	public ResponseEntity<String> handleNoExportStrategyFoundException(final NoExportStrategyFoundException ex)
	{
		return new ResponseEntity<>(FreeDesignConstants.GENERAL_EXCEPTION, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ArtWorksExportException.class)
	public ResponseEntity<String> handleArtWorksExportException(final ArtWorksExportException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnexpectedException(final Exception ex)
	{
		return new ResponseEntity<>(FreeDesignConstants.GENERAL_EXCEPTION + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
