
package team9.tutoragency.controller;

import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.validation.SignupFormValidator;
import util.SignupFormValidatorMock;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static java.util.Arrays.asList;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

	private SignupFormValidator validator;
    
	@InjectMocks
    private RegisterController controller;
    
    private MockMvc mockMvc;
    @Before
    public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    /**
     * Test of register method, of class {@link RegisterController}.
     */
    @Test
    public void testRegister() throws Exception {
    	
    	mockMvc.perform(get("/register")).andExpect(status().isOk())
    		.andExpect(forwardedUrl("signupPage"))
    		.andExpect(model().attribute("signupForm", new SignupForm()));
    }
    
    @Test
    public void testCreateMember_formRejected() throws Exception{
    	SignupForm form = new SignupForm();
    	form.setUsername("username");
    	
    	validator = new SignupFormValidatorMock(true);	
    	controller.setValidator(validator);
    	
    	mockMvc.perform(post("/create").param("username", "username"))
    			.andExpect(status().isOk())
    			.andExpect(forwardedUrl("signupPage"))
    			.andExpect(model().attribute("signupForm", form));
    }
   
    
    /**
     * Test of createUser method, of class {@link RegisterController}. Tests the correctness of the
     * returned view, if validation is not successful.
     */
//    @Test
//    public void createUserErrors() throws Exception {
//        SignupForm form = new SignupForm();
//        BindingResult error = new DirectFieldBindingResult(form, "signupForm");
//        error.reject("test error");
//        ModelAndView result = controller.createMember(form, error, null);
//        assertEquals("register", result.getViewName());
//        
//    }
    /**
     * Test of createUser method, of class {@link RegisterController}. Tests the correctness of the
     * returned view, if validation is successful.
     */
//    @Test
//    public void createUserSuccess() throws Exception {
//        SignupForm form = new SignupForm();
//        BindingResult error = new DirectFieldBindingResult(form, "signupForm");
//        ModelAndView result = controller.createMember(form, error, null);
//        assertEquals("registerSuccess", result.getViewName());   
//    }
    
}
