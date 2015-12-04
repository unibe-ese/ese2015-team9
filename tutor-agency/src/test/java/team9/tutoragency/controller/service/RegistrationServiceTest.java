
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
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private RegistrationService service;
    @Captor
    private ArgumentCaptor<Member> captor;
    
    @Before
    public void setUp() {
        // Capture the Member that is given to the Dao. The return value doesn't matter in this context.
        Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));
    }

    /**
    * Test of saveFrom method, of class {@link RegistrationService}.
    */
    @Test
    public void testSaveFrom() {
        SignupForm form = new SignupForm();
        form.setFirstName("firstName");
        form.setLastName("lastName");
        form.setEmail("member@email.com");
        form.setUsername("username");
        form.setPassword("password");
        Member expectedMember = new Member("firstName", "lastName", "member@email.com", "username",
                DigestUtils.md5Hex("password"));
        service.saveFrom(form);
        assertTrue(expectedMember.equals(captor.getValue()));
        
    }
    
}
