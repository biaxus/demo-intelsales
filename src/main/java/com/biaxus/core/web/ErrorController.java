package com.biaxus.core.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	private Logger log = Logger.getLogger(ErrorController.class.getCanonicalName());
	
	@RequestMapping(value = { "/404" })
	public String _404() {
		return "404";
	}

	@RequestMapping(value = { "/500" })
	public String _500() {
		return "500";
	}

}
