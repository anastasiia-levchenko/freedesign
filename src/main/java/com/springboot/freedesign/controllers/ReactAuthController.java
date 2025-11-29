package com.springboot.freedesign.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReactAuthController {

	@GetMapping("/status")
	public Map<String, Boolean> status(@AuthenticationPrincipal Object user) {
		return Map.of("authenticated", user != null);
	}
}