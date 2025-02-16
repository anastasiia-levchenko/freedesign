package com.springboot.freedesign.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/home")
public class AdminController
{
	@GetMapping({ "", "/" })
	public String getDefaultAdminPage(final Model model)
	{
		return "admin/adminHome";
	}
}
