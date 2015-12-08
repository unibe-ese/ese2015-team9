
package team9.tutoragency.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.validation.SignupFormValidator;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for class {@link SignupController}.
 * @author bruno
 * @author curtys
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupControllerUnitTest {
	@Mock
	private SignupFormValidator validator;   
	@Mock
	private AccountService accountService;
	@InjectMocks
    private SignupController controller = new SignupController();    
    
    /**
     * Test of createUser method, of class {@link SignupController}. Tests the correctness of the
     * returned view, if validation is not successful.
     */
    @Test
    public void test_createUserErrors() throws Exception {
        SignupForm form = new SignupForm();
        
        BindingResult error = mock(BindingResult.class);
        when(error.hasErrors()).thenReturn(true);
        
        ModelAndView result = controller.createMember(form, error);
        assertEquals("signupPage", result.getViewName());
        assertEquals(form, result.getModel().get("signupForm"));
        
    }
    
    /**
     * Test of createUser method, of class {@link SignupController}. Tests the correctness of the
     * returned view, if validation is successful.
     */
    @Test
    public void test_createUserSuccess() throws Exception {
        SignupForm form = new SignupForm();
        BindingResult error = mock(BindingResult.class);
        when(error.hasErrors()).thenReturn(false);
    
        ModelAndView result = controller.createMember(form, error);
        assertEquals("registerSuccess", result.getViewName());   
    }
    
}
