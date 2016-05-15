package com.biaxus.core.web.view.user;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biaxus.core.domain.Role;
import com.biaxus.core.domain.User;
import com.biaxus.core.service.RoleService;
import com.biaxus.core.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserValidator userValidator;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(User user, Model model) {
		List<User> userList = userService.findAllSellers();
		model.addAttribute("userList", userList);
		initModel(user, model, false);
		return "user/abm";
	}

	private void initModel(User user, Model model, boolean saveSuccess) {
		model.addAttribute("user", user);
		model.addAttribute("isNew", false);
		model.addAttribute("saveSuccess", saveSuccess);
	}

	@RequestMapping(value = { "/fn/user" }, method = RequestMethod.GET)
	public String addUser(User user, Model model, Principal principal,
			@RequestParam("saveSuccess") boolean saveSuccess) {
		initModel(user, model, saveSuccess);
		return "user/user-form :: user-form";
	}

	@RequestMapping(value = "/fn/user", method = RequestMethod.POST)
	public String save(@Valid User user, BindingResult bindingResult, ModelMap model, Principal principal) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("isNew", true);
			return "user/user-form :: user-form";
		}
		boolean isNew = user.getUsername() == null || user.getUsername().trim().isEmpty();
		if (isNew) {
			Authentication authentication = ((Authentication) principal);
			User currentuser = (User) authentication.getPrincipal();
			HashSet<Role> roleList = new HashSet<>();
			Role seller = roleService.findOne("SELLER");
			roleList.add(seller);
			user.setRoleList(roleList);
			user.setUsername(UUID.randomUUID().toString());
			user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
			user.setAccountNonExpired(false);
			user.setAccountNonLocked(false);
			user.setCredentialsNonExpired(false);
			user.setEnabled(false);
			user.setEnterprise(currentuser.getEnterprise());
		} else {
			User _user = userService.findOne(user.getUsername());
			_user.setUsername(user.getUsername());
			_user.setLastName(user.getLastName());
			_user.setName(user.getName());
			user = _user;
		}
		userService.save(user);
		model.clear();
		return "redirect:/fn/user?saveSuccess=true";
	}

	@RequestMapping(value = "/fn/user/cancel", method = RequestMethod.GET)
	public String cancel(ModelMap model) {
		model.clear();
		return "redirect:/fn/user?saveSuccess=false";
	}

	@RequestMapping(value = { "/fn/user/edit" }, method = RequestMethod.POST)
	public String addNew(User user, BindingResult bindingResult, Model model,
			@RequestParam(name = "username", required = false) String username) {
		if (username != null) {
			User _user = userService.findOne(username);
			if (_user != null) {
				user.setUsername(_user.getUsername());
				user.setLastName(_user.getLastName());
				user.setName(_user.getName());
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		return "user/user-form :: user-form";
	}

	@RequestMapping(value = { "/fn/user/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<User> userList = userService.findAllSellers();
		model.addAttribute("userList", userList);
		return "user/user-list :: user-list";
	}

	@RequestMapping(value = { "/fn/user/{username}" }, method = RequestMethod.DELETE)
	public String deleteEnterprise(@PathVariable String username) {
		String level = "success";
		String message = "sucessfullDelete";
		userService.delete(username);
		return "basic/alert :: alert(level='" + level + "',message='" + message + "')";
	}
}
