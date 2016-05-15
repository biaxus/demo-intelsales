package com.biaxus.core.web.view.client;

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

import com.biaxus.core.domain.Client;
import com.biaxus.core.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientValidator clientValidator;
	@Autowired
	private ClientService clientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(clientValidator);
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String client(Client client, Model model) {
		List<Client> clientList = clientService.findAll();
		model.addAttribute("clientList", clientList);
		initModel(client, model, false);
		return "client/abm";
	}

	private void initModel(Client client, Model model, boolean saveSuccess) {
		model.addAttribute("client", client);
		model.addAttribute("isNew", false);
		model.addAttribute("saveSuccess", saveSuccess);
	}

	@RequestMapping(value = { "/fn/client" }, method = RequestMethod.GET)
	public String addClient(Client client, Model model, Principal principal,
			@RequestParam("saveSuccess") boolean saveSuccess) {
		initModel(client, model, saveSuccess);
		return "client/client-form :: client-form";
	}

	@RequestMapping(value = "/fn/client", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult bindingResult, ModelMap model, Principal principal) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("isNew", true);
			return "client/client-form :: client-form";
		}
		clientService.save(client);
		model.clear();
		return "redirect:/fn/client?saveSuccess=true";
	}

	@RequestMapping(value = "/fn/client/cancel", method = RequestMethod.GET)
	public String cancel(ModelMap model) {
		model.clear();
		return "redirect:/fn/client?saveSuccess=false";
	}

	@RequestMapping(value = { "/fn/client/edit" }, method = RequestMethod.POST)
	public String addNew(Client client, BindingResult bindingResult, Model model,
			@RequestParam(name = "id", required = false) Long id) {
		if (id != null) {
			Client _client = clientService.findOne(id);
			if (_client != null) {
				client.setId(_client.getId());
				client.setDescription(_client.getDescription());
				client.setName(_client.getName());
			}
		}
		model.addAttribute("client", client);
		model.addAttribute("isNew", true);
		return "client/client-form :: client-form";
	}

	@RequestMapping(value = { "/fn/client/list" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<Client> clientList = clientService.findAll();
		model.addAttribute("clientList", clientList);
		return "client/client-list :: client-list";
	}
	
	@RequestMapping(value = { "/fn/client/{id}" }, method = RequestMethod.DELETE)
	public String deleteEnterprise(@PathVariable Long id) {
		String level = "success";
		String message = "sucessfullDelete";
		clientService.delete(id);
		return "basic/alert :: alert(level='"+level+"',message='"+message+"')";
	}
}
