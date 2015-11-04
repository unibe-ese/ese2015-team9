
package team9.tutoragency.controller;

import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations = {"classpath:/test-springMVC.xml", "classpath:/test-springData.xml", "classpath:/test-springSecurity.xml"})
public class SignupFormValidatorTest {
    
//    @Autowired
//    private SignupFormValidator validator;   
//    @Resource
//    MemberDao dao;
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private final SignupFormValidator validator = new SignupFormValidator();
    
    
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
    
    private SignupForm createForm() {
        SignupForm form = new SignupForm();
        form.setReadAGB(true);
        form.setPassword("password");
        form.setPasswordConfirm("password");
        return form;
    }
    
}
