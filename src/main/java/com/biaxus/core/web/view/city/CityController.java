package com.biaxus.core.web.view.city;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.biaxus.core.domain.City;
import com.biaxus.core.service.CityService;

@Controller
public class CityController {

	@Autowired
	private CityValidator cityValidator;
	@Autowired
	private CityService cityService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(cityValidator);
	}

	@RequestMapping(value = "/city", method = RequestMethod.GET)
	public String city(City city, Model model) {
		List<City> cityList = cityService.findAll();
		model.addAttribute("cityList", cityList);
		initModel(city, model, false);
		return "city/abm";
	}

	private void initModel(City city, Model model, boolean saveSuccess) {
		model.addAttribute("city", city);
		model.addAttribute("isNew", false);
		model.addAttribute("saveSuccess", saveSuccess);
	}

	@RequestMapping(value = { "/fn/city" }, method = RequestMethod.GET)
	public String addCity(City city, Model model, Principal principal,
			@RequestParam("saveSuccess") boolean saveSuccess) {
		initModel(city, model, saveSuccess);
		return "city/city-form :: city-form";
	}

	@RequestMapping(value = "/fn/city", method = RequestMethod.POST)
	public String save(@Valid City city, BindingResult bindingResult, ModelMap model, Principal principal) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("isNew", true);
			return "city/city-form :: city-form";
		}
		cityService.save(city);
		model.clear();
		return "redirect:/fn/city?saveSuccess=true";
	}

	@RequestMapping(value = "/fn/city/cancel", method = RequestMethod.GET)
	public String cancel(ModelMap model) {
		model.clear();
		return "redirect:/fn/city?saveSuccess=false";
	}

	@RequestMapping(value = { "/fn/city/edit" }, method = RequestMethod.POST)
	public String addNew(City city, BindingResult bindingResult, Model model,
			@RequestParam(name = "id", required = false) Long id) {
		if (id != null) {
			City _city = cityService.findOne(id);
			if (_city != null) {
				city.setId(_city.getId());
				city.setName(_city.getName());
			}
		}
		model.addAttribute("city", city);
		model.addAttribute("isNew", true);
		return "city/city-form :: city-form";
	}

	@RequestMapping(value = { "/fn/city/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<City> cityList = cityService.findAll();
		model.addAttribute("cityList", cityList);
		return "city/city-list :: city-list";
	}
}
