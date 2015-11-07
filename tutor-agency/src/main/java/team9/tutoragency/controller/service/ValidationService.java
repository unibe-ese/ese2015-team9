package team9.tutoragency.controller.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import team9.tutoragency.controller.pojos.Form;

@Service
public interface ValidationService {
	
	public void validate(Form form, Errors errors);

}
