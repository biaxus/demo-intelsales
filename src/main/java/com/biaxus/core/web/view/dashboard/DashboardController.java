package com.biaxus.core.web.view.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = { "/dashboard/dashboardComercialDivision" }, method = RequestMethod.GET)
	public String dashboardComercialDivision() {
		return "dashboard/dashboardComercialDivision";
	}
	
	@RequestMapping(value = { "/dashboard/dashboardUnits" }, method = RequestMethod.GET)
	public String dashboardComercialUnits() {
		return "dashboard/dashboardUnits";
	}
	
	@RequestMapping(value = { "/dashboard/dashboardUnitsDivision" }, method = RequestMethod.GET)
	public String dashboardUnitsDivision() {
		return "dashboard/dashboardUnitsDivision";
	}
	
	@RequestMapping(value = { "/dashboard/detail" }, method = RequestMethod.GET)
	public String detail(ModelMap model) {
		model.addAttribute("showSidebar", true);
		return "dashboard/detail";
	}
	
	@RequestMapping(value = { "/dashboard/detailYearMobile" }, method = RequestMethod.GET)
	public String detailYearMobile(ModelMap model) {
		model.addAttribute("showSidebar", true);
		return "dashboard/detailYearMobile";
	}
	
}
