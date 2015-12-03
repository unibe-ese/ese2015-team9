
package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
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
import team9.tutoragency.controller.service.validation.SignupFormValidationService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

/**
 * Tests the class {@link SignupFormValidationService}
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupFormValidationServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private final SignupFormValidationService validator = new SignupFormValidationService();
    
    @Before
    public void setUp() {
        Member member = new Member("firstName", "lastName", "member@email.com", "username", "password", "description");
        List<Member> members = new ArrayList<Member>();
        members.add(member);
        Mockito.when(memberDao.findByUsername("username")).thenReturn(members);
        Mockito.when(memberDao.findByEmail("member@email.com")).thenReturn(members);
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
        form.setPassword("password");
        form.setPasswordConfirm("password");
        return form;
    }
    
}
