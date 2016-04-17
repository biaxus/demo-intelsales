package com.biaxus.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = { "/dashboard/detail" }, method = RequestMethod.GET)
	public String detail() {
		return "dashboard/detail";
	}
	
}
