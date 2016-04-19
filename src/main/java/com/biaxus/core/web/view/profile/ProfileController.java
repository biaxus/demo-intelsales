package com.biaxus.core.web.view.profile;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biaxus.core.domain.User;
import com.biaxus.core.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileValidator profileValidator;
	
	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(profileValidator);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Profile profile, Model model, Principal principal) {
		initModel(profile, model, principal, false);
		return "user/profile-update";
	}
	
	private void initModel(Profile profile, Model model, Principal principal, boolean saveSuccess){
		if (principal != null) {
			Authentication authentication = ((Authentication) principal);
			User user = (User) authentication.getPrincipal();
			profile.setUsername(user.getUsername());
			profile.setLastName(user.getLastName());
			profile.setName(user.getName());
		}
		model.addAttribute("profile", profile);
		model.addAttribute("isEdit", false);
		model.addAttribute("saveSuccess", saveSuccess);
	}
	
	@RequestMapping(value = { "/fn/profile/edit" }, method = RequestMethod.POST)
	public String addEdit(Profile profile,BindingResult bindingResult, Model model) {
		model.addAttribute("isEdit", true);
		return "user/profile-update-form :: profile-update-form";
	}
	
	@RequestMapping(value = { "/fn/profile" }, method = RequestMethod.GET)
	public String addProfile(Profile profile, Model model, Principal principal, @RequestParam("saveSuccess") boolean saveSuccess) {
		initModel(profile, model, principal, saveSuccess);
		
		return "user/profile-update-form :: profile-update-form";
	}
	
	@RequestMapping(value = "/fn/profile", method = RequestMethod.POST)
	public String save(@Valid Profile profile, BindingResult bindingResult, ModelMap model, Principal principal) {
		if (bindingResult.hasErrors()){
			model.addAttribute("isEdit", true);
			return "user/profile-update-form :: profile-update-form";
		}
		User user = null;
		if (principal != null) {
			Authentication authentication = ((Authentication) principal);
			user = (User) authentication.getPrincipal();
		}
		user.setName(profile.getName());
		user.setLastName(profile.getLastName());
		user = userService.findOne(user.getUsername());
		user.setName(profile.getName());
		user.setLastName(profile.getLastName());
		userService.save(user);
		model.clear();
		return "redirect:/fn/profile?saveSuccess=true";
	}
	
	@RequestMapping(value = "/fn/profile/cancel", method = RequestMethod.GET)
	public String cancel(ModelMap model) {
		model.clear();
		return "redirect:/fn/profile";
	}

}
