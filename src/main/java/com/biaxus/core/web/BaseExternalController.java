package com.biaxus.core.web;

import java.security.Principal;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaxus.core.domain.Setting;
import com.biaxus.core.domain.User;

@Controller
public class BaseExternalController {

	private Logger log = Logger.getLogger(BaseExternalController.class.getCanonicalName());

	@RequestMapping(value = { "/" })
	public String home(Principal principal) {
		String home = "index";
		if (principal != null) {
			Authentication authentication = ((Authentication) principal);
			User user = (User) authentication.getPrincipal();
			String _home = user.getSettings().get(Setting.HOME_PAGE);
			home = _home != null && !_home.isEmpty() ? _home : home;
		}
		return home;
	}

	@RequestMapping(value = "/login")
	public String loginRedirect(Principal user) {
		if (user == null || !((Authentication) user).isAuthenticated())
			return "login";
		return "redirect:/";
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model, Principal user) {
		model.addAttribute("message", "sucessfulLoggedOut");
		model.addAttribute("level", "success");
		return loginRedirect(user);
	}

	@RequestMapping(value = "/sessionAuthenticationError")
	public String sessionAuthenticationError(Model model, Principal user) {
		model.addAttribute("message", "sessionAuthenticationError");
		model.addAttribute("level", "danger");
		return loginRedirect(user);
	}

	@RequestMapping(value = "/loginFail")
	public String loginFail(Model model, Principal user) {
		model.addAttribute("message", "loginFail");
		model.addAttribute("level", "warning");
		return loginRedirect(user);
	}

	@RequestMapping(value = "/invalidSession")
	public String invalidSession(Model model, Principal user) {
		model.addAttribute("message", "invalidSession");
		model.addAttribute("level", "warning");
		return loginRedirect(user);
	}

	@RequestMapping(value = "/invalidRememberMe")
	public String invalidRememberMe(Model model, Principal user) {
		model.addAttribute("message", "invalidRememberMe");
		model.addAttribute("level", "danger");
		return loginRedirect(user);
	}

}
