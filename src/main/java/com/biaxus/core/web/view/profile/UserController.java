package com.biaxus.core.web.view.profile;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/profile")
	public String user(Principal user) {
		return "user/profile-update";
	}
	
}
