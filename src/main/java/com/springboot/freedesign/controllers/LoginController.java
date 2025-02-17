package com.springboot.freedesign.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.springboot.freedesign.common.FreeDesignConstants.LOGIN_URL;


@Controller
class LoginController
{
	@GetMapping(LOGIN_URL)
	public String login()
	{
		return "login";
	}
}