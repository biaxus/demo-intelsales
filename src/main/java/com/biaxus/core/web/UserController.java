package com.biaxus.core.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
	
	@RequestMapping("/profile")
	public String user(Principal user) {
		return "user/profileUpdate";
	}
	
}
