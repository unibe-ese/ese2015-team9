
package team9.tutoragency.controller;

import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.SignupFormValidationService;
import team9.tutoragency.controller.service.SignupFromSaveService;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {
    

	@Mock
	private SignupFromSaveService saveService;
	@Mock
	private SignupFormValidationService validator;
    @InjectMocks
    private RegisterController controller;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of register method, of class {@link RegisterController}.
     */
    @Test
    public void testRegister() throws Exception {
        HttpServletResponse response = null;
        ModelAndView expResult = new ModelAndView("register", "signupform", new SignupForm());
        ModelAndView result = controller.register(response);
        assertEquals((SignupForm)expResult.getModelMap().get("signupform"), 
                (SignupForm)result.getModelMap().get("signupForm"));
        assertEquals(expResult.getViewName(), result.getViewName());
    }
    /**
     * Test of createUser method, of class {@link RegisterController}. Tests the correctness of the
     * returned view, if validation is not successful.
     */
    @Test
    public void createUserErrors() throws Exception {
        SignupForm form = new SignupForm();
        BindingResult error = new DirectFieldBindingResult(form, "signupForm");
        error.reject("test error");
        ModelAndView result = controller.createUser(form, error, null);
        assertEquals("register", result.getViewName());
        
    }
    /**
     * Test of createUser method, of class {@link RegisterController}. Tests the correctness of the
     * returned view, if validation is successful.
     */
    @Test
    public void createUserSuccess() throws Exception {
        SignupForm form = new SignupForm();
        BindingResult error = new DirectFieldBindingResult(form, "signupForm");
        ModelAndView result = controller.createUser(form, error, null);
        assertEquals("registerSuccess", result.getViewName());   
    }
    
}
