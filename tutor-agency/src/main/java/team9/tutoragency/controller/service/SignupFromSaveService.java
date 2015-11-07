package team9.tutoragency.controller.service;

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.SignupForm;


public interface SignupFromSaveService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

}