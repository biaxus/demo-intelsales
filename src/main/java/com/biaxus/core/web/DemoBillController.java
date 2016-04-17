package com.biaxus.core.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biaxus.core.domain.DemoBill;
import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.service.DemoBillService;

@Controller
public class DemoBillController {

	@Autowired
	private DemoBillService service;

	@RequestMapping(value = { "/fn/demobill/list" }, method = RequestMethod.GET)
	public String abm(Enterprise enterprise, Model model) {
		List<DemoBill> demoBillList = service.findAll();
		model.addAttribute("demobillList", demoBillList);
		return "dashboard/detail-table :: detail-table";
	}

}
