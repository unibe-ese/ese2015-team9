
package team9.tutoragency.controller.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @InjectMocks
    private final RegistrationService service = new RegistrationService();
    @Captor
    private ArgumentCaptor<Member> captor;
    
    @Before
    public void setUp() {
//        Mockito.when(memberDao.save(any(Member.class))).thenAnswer(new Answer<Member>() {
//            @Override
//            public Member answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                return (Member) args[0];
//            }
//        });
        
        Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));
    }
    
    @After
    public void tearDown() {
    }

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
//        System.out.println(captor.getValue().toString());
        assertTrue(expectedMember.equals(captor.getValue()));
        
    }
    
}
