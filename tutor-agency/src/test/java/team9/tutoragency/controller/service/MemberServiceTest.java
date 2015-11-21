
package team9.tutoragency.controller.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

import static org.mockito.Mockito.*;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @Mock
    private UniversityDao uniDao;
    @InjectMocks
    private MemberService service;
    @Captor
    private ArgumentCaptor<Member> captor;
    
    @Before
    public void setUp() {
        // Capture the Member that is given to the Dao. The return value doesn't matter in this context.
        Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));
    }

    /**
     * Test of upgradeToTutor method, of class {@link MemberService}.
     */
    @Test
    public void testUpgradeToTutor() {
        Member member = new Member("firstName", "lastName", "member@email.com", "username", "password");
       
        Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(authentication.isAuthenticated()).thenReturn(true);
		Mockito.when(authentication.getPrincipal()).thenReturn(member);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		SecurityContextHolder.setContext(securityContext);
        
        member.setIsTutor(true);
        assertEquals(member, service.upgradeAuthenticatedMemberToTutor());
    }

    /**
     * Test of saveEditChange method, of class {@link MemberService}.
     */
    @Test
    public void testSaveEditChange() {
        Member expMember = new Member("fName", "lName", "newemail@email.com", "uname", "password");
        Member memberBefore = new Member("firstName", "lastName", "member@email.com", "username", "password");
        
        EditForm form = new EditForm();
        form.setEmail(expMember.getEmail());
        form.setFirstName(expMember.getFirstName());
        form.setLastName(expMember.getLastName());
        form.setUsername(expMember.getUsername());
        
        service.saveEditChange(memberBefore, form);
        assertEquals(expMember, captor.getValue());
        
        expMember.setPassword(DigestUtils.md5Hex("newpassword"));
        form.setPassword("newpassword");
        service.saveEditChange(memberBefore, form);
        assertEquals(expMember, captor.getValue());
        
        form.setFee("20.0");
        service.saveEditChange(memberBefore, form);
        assertEquals(expMember, captor.getValue());
        
        expMember.setIsTutor(true);
        expMember.setFee(20.0);
        memberBefore.setIsTutor(true);
        service.saveEditChange(memberBefore, form);
        assertEquals(expMember, captor.getValue());
    }
    
    
}
