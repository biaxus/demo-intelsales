package com.biaxus.core.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.service.EnterpriseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
@RequestMapping("/rest/enterprise")
public class EnterpriseRestController {

	@Autowired
	EnterpriseService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Enterprise> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Enterprise findOne(@PathVariable String key) {
		return service.findOne(key);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Enterprise addItem(@RequestBody Enterprise enterprise) {
		enterprise.setId(null);
		return service.save(enterprise);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Enterprise updateItem(@RequestBody Enterprise enterprise, @PathVariable String key) {
		enterprise.setId(key);
		return service.save(enterprise);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable String key) {
		service.delete(key);
	}

	@RequestMapping(value = "/findWithCycle", method = RequestMethod.GET)
	public String findWithCycle() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(service.findWithCycle());
	}

}
