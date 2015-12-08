package team9.tutoragency.controller.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.impl.AccountServiceImpl;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

/**
 * Unit tests for the class {@code AccountServiceImpl}.
 * @see {@link AccountService}, {@link AccountServiceImpl}.
 * @author bruno
 * @author curtys
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	 	@Mock
	    private MemberDao memberDao;
	    @Mock
	    private UniversityDao uniDao;
	    @InjectMocks
	    private AccountServiceImpl service;
	    @Captor
	    private ArgumentCaptor<Member> captor;
	    private final Member activeMember = new Member("firstName", "lastName", "member@email.com", "username", "password");
	    
	    @Before
	    public void setUp() {
	        Authentication authentication = Mockito.mock(Authentication.class);
			SecurityContext securityContext = Mockito.mock(SecurityContext.class);
			Mockito.when(authentication.isAuthenticated()).thenReturn(true);
			Mockito.when(authentication.getPrincipal()).thenReturn(activeMember);
			Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);	
			SecurityContextHolder.setContext(securityContext);
	        Mockito.when(memberDao.findOne(anyLong())).thenReturn(activeMember);
	    }

	    /**
	     * Test of saveEditChange method, of class {@link AccountServiceImpl}.
	     */
	    @Test
	    public void testSaveEditChange() {
	        Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));
	        
	        Member expMember = new Member("fName", "lName", "newemail@email.com", "uname", "password");
	        Member memberBefore = new Member("firstName", "lastName", "member@email.com", "username", "password");
	        
	        EditForm form = new EditForm();
	        form.setEmail(expMember.getEmail());
	        form.setFirstName(expMember.getFirstName());
	        form.setLastName(expMember.getLastName());
	        form.setUsername(expMember.getUsername());
	        
	        service.updateAccount(memberBefore, form);
	        assertEquals(expMember, captor.getValue());
	        
	        expMember.setPassword(DigestUtils.md5Hex("newpassword"));
	        form.setPassword("newpassword");
	        service.updateAccount(memberBefore, form);
	        assertEquals(expMember, captor.getValue());
	        
	        form.setFee("20.0");
	        service.updateAccount(memberBefore, form);
	        assertEquals(expMember, captor.getValue());
	        
	        expMember.setIsTutor(true);
	        expMember.setFee(20.0);
	        memberBefore.setIsTutor(true);
	        service.updateAccount(memberBefore, form);
	        assertEquals(expMember, captor.getValue());
	    }
	    
	    /**
	     * Test of create member method, of class {@link AccountServiceImpl}.
	     */
	     @Test
	     public void testCreateMember() {
	    	 
	         Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));

	         SignupForm form = new SignupForm();
	         form.setFirstName("firstName");
	         form.setLastName("lastName");
	         form.setEmail("member@email.com");
	         form.setUsername("username");
	         form.setPassword("password");
	         Member expectedMember = new Member("firstName", "lastName", "member@email.com", "username",
	                 DigestUtils.md5Hex("password"));
	         service.createAccount(form);
	         assertTrue(expectedMember.equals(captor.getValue()));
	         
	     }
}
