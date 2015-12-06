
package team9.tutoragency.controller.service;

import java.util.Optional;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.impl.AccountServiceImpl;
import team9.tutoragency.controller.service.impl.MemberServiceImpl;
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
    private MemberServiceImpl service;
   
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
     * Test of getAuthenticatedMember method, of class {@link AccountServiceImpl}.
     */
    @Test
    public void testGetAuthenticatedMember() {
        Optional<Member> result = service.getAuthenticatedMember();
        assertEquals(activeMember, result.get());
        
        Mockito.when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(null);
        result = service.getAuthenticatedMember();
        assertEquals(Optional.empty(), result);
    }


}
