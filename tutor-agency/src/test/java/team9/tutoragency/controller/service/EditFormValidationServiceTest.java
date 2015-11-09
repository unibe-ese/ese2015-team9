
package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

/**
 * Tests of the class {@link EditFormValidationService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class EditFormValidationServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private EditFormValidationService service;
    
    @Before
    public void setUp() {
        Member activeMember = new Member("fName", "lName", "m@email.com", "uname", DigestUtils.md5Hex("password"));
        activeMember.setIsTutor(true);
        activeMember.setId(Long.valueOf(1));
        Member controllMember = new Member("firstName", "lastName", "member@email.com", "username", "password");
        controllMember.setId(Long.valueOf(2));
        List<Member> members = new ArrayList<Member>();
        members.add(controllMember);
        Mockito.when(
                memberDao.findByUsername("username"))
                .thenReturn(members);
        Mockito.when(
                memberDao.findByEmail("member@email.com"))
                .thenReturn(members);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(authentication.getPrincipal()).thenReturn(activeMember);
    }

    @Test
    public void usernameAlreadyInUse() {
        EditForm form = createEditForm();
        form.setUsername("username");
        Errors error = new DirectFieldBindingResult(form, "editform");
        service.validate(form, error);
        System.out.println(error.toString());
        assertTrue(error.hasErrors());
    }
    
    @Test
    public void emailAlreadyInUse() {
        EditForm form = createEditForm();
        form.setEmail("member@email.com");
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        assertTrue(error.hasErrors());
    }
    
    @Test
    public void invalidUsername() {
        EditForm form = createEditForm();
        form.setUsername("hi");
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        assertTrue(error.hasErrors());
    }
    
    @Test
    public void oldPasswordMismatch() {
        EditForm form = createEditForm();
        form.setOldPassword("pswrd");
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        assertTrue(error.hasErrors());
    }
    
    @Test
    public void newPasswordMismatch() {
        EditForm form = createEditForm();
        form.setPasswordConfirm("pswrd");
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        assertTrue(error.hasErrors());
    }
    
    @Test
    public void invalidFeeValue() {
        EditForm form = createEditForm();
        form.setFee("abc");
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        assertTrue(error.hasErrors());
    }
    /**
     * Tests if a valid form passes the validation without error.
     */    
    @Test
    public void positiveControllTest() {
        EditForm form = createEditForm();
        Errors error = new DirectFieldBindingResult(form, "editform");             
        service.validate(form, error);
        
        assertFalse(error.hasErrors());
    }
    /**
     * Creates a valid form, i.e. a form that passes the validation without error.
     * @return EditForm form
     */    
    private EditForm createEditForm() {
        EditForm form = new EditForm();
        form.setUsername("uname");
        form.setEmail("m@email.com");
        form.setOldPassword("password");
        form.setFee("20.0");
        form.setPassword("password");
        form.setPasswordConfirm("password");
        return form;
    }
    
}
