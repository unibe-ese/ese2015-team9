package team9.tutoragency.controller.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import team9.tutoragency.controller.pojos.Form;

/**
 * Defines an interface for a {@link org.hibernate.service.Service} which
 * provides the function to validate a {@link Form}.
 * 
 * @author laeri
 *
 */
@Service
public interface ValidationService {

	public void validate(Form form, Errors errors);

}
