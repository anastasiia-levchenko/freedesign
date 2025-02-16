package com.springboot.freedesign.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.springboot.freedesign.common.FreeDesignConstants.ACCESS_DENIED_PAGE;


@Controller
@RequestMapping("/error")
public class ErrorController
{
	@GetMapping("/accessDenied")
	public String accessDeniedPage()
	{
		return ACCESS_DENIED_PAGE;
	}
}
