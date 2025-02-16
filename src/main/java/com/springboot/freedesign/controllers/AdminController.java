package com.springboot.freedesign.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.springboot.freedesign.common.FreeDesignConstants.ADMIN_HOME_PAGE;
import static com.springboot.freedesign.common.FreeDesignConstants.ADMIN_HOME_URL;


@Controller
@RequestMapping(ADMIN_HOME_URL)
public class AdminController
{
	@GetMapping({ "", "/" })
	public String getDefaultAdminPage(final Model model)
	{
		return ADMIN_HOME_PAGE;
	}
}
