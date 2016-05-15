package com.biaxus.core.web.view.city;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.biaxus.core.domain.City;

@Component
public class CityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(City.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
	}	
}
