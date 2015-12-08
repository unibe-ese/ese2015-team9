
package team9.tutoragency.controller.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.validation.SignupFormValidator;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;
import static java.util.Arrays.asList;
/**
 * Unit test for the class {@link SignupFormValidator}.
 * @author curtys
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupFormValidatorTest {
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private final SignupFormValidator validator = new SignupFormValidator();
    
    @Before
    public void setUp() {
        Member member = new Member("firstName", "lastName", "member@email.com", "username", "password");
        
        Mockito.when(memberDao.findByUsername("username")).thenReturn(asList(member));
        Mockito.when(memberDao.findByEmail("member@email.com")).thenReturn(asList(member));
    }
    
    @Test
    public void passwordMismatch() {
        SignupForm form = createForm();
        form.setPassword("password1");
        form.setPasswordConfirm("password2");
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertTrue(error.hasErrors());
    }
    @Test
    public void passwordMatch() {
        SignupForm form = createForm();
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertFalse(error.hasErrors());
    }
    @Test
    public void checkAGB() {
        SignupForm form = createForm();
        form.setReadAGB(false);
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertTrue(error.hasErrors());
    }
    @Test
    public void invalidUsername() {
        SignupForm form = createForm();
        form.setUsername("hi");
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertTrue(error.hasErrors());
    }
    @Test
    public void usernameAlreadyInUse() {
        SignupForm form = createForm();
        form.setUsername("username");
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertTrue(error.hasErrors());
    }
    @Test
    public void emailAlreadyInUse() {
        SignupForm form = createForm();
        form.setEmail("member@email.com");
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertTrue(error.hasErrors());
    }
    /**
     * Tests if a valid form passes the validation without error.
     */
    @Test
    public void positiveControllTest() {
        SignupForm form = createForm();
        Errors error = new DirectFieldBindingResult(form, "signupform");
        validator.validate(form, error);
        assertFalse(error.hasErrors());
    }
    /**
     * Creates a valid form, i.e. a form that passes the validation without error.
     * @return SignupForm form
     */
    private SignupForm createForm() {
        SignupForm form = new SignupForm();
        form.setReadAGB(true);
        form.setUsername("Username");
        form.setLastName("lastName");
        form.setFirstName("firstName");
        form.setEmail("testMail@mail.com");
        form.setPassword("password");
        form.setPasswordConfirm("password");
        return form;
    }
    
}
