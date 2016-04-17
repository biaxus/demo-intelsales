package com.biaxus.core.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.domain.EnterpriseRole;
import com.biaxus.core.service.EnterpriseService;

@Controller
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private EnterpriseValidator enterpriseValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(enterpriseValidator);
	}

	@RequestMapping(value = { "/enterprise/abm" }, method = RequestMethod.GET)
	public String abm(Enterprise enterprise, Model model) {
		List<Enterprise> enterpriseList = enterpriseService.findAll();
		model.addAttribute("enterpriseList", enterpriseList);
		return "enterprise/abm";
	}

	@RequestMapping(value = { "/fn/enterprise" }, method = RequestMethod.GET)
	public String addEnterprise(Enterprise enterprise) {
		return "enterprise/enterprise-form :: enterprise-form";
	}

	@RequestMapping(value = { "/fn/enterprise/{id}" }, method = RequestMethod.DELETE)
	public String deleteEnterprise(@PathVariable String id) {
		String level = "success";
		String message = "sucessfullDelete";
		enterpriseService.delete(id);
		return "basic/alert :: alert(level='"+level+"',message='"+message+"')";
	}

	
	@RequestMapping(value = { "/fn/enterprise" }, method = RequestMethod.POST)
	public String addEnterprise(@Valid Enterprise enterprise, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors())
			return "enterprise/enterprise-form :: enterprise-form";
		enterpriseService.save(enterprise);
		model.clear();
		return "redirect:/fn/enterprise";
	}

	@RequestMapping(value = { "/fn/enterprise/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<Enterprise> enterpriseList = enterpriseService.findAll();
		model.addAttribute("enterpriseList", enterpriseList);
		return "enterprise/enterprise-list :: enterprise-list";
	}

}
