package com.biaxus.core.web.view.enterprise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.service.EnterpriseService;

@Component
public class EnterpriseValidator implements Validator {

	@Autowired
	private EnterpriseService enterpriseService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Enterprise.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
		Enterprise enterprise = (Enterprise) target;
		List<Enterprise> existingEnterpriseList = enterpriseService.findByDescription(enterprise.getDescription());
		if (!existingEnterpriseList.isEmpty()) {
			errors.rejectValue("description", "description.alrredyExist");
		}
	}

}
