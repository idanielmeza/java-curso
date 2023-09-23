package com.ledezmadev.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IndentificadorRegexValidador implements ConstraintValidator<IndentificadorRegex, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value.matches("^[0-9a-fA-F]{8}\\\\b-[0-9a-fA-F]{4}\\\\b-[0-9a-fA-F]{4}\\\\b-[0-9a-fA-F]{4}\\\\b-[0-9a-fA-F]{12}$")) {
			return true;
		}
		return false;
	}

}
