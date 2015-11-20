package team9.tutoragency.controller;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
	@Mock
	MemberService memberService;
	@InjectMocks
	ProfileController controller;
	
//	public final static String USERNAME = "username";
	public final static Member member = new Member();
	@Before
	public void setUp() {
		when(memberService.findById(any(Long.class))).thenReturn(member, null);
	}
	
	@Test
	public void showProfile(){
		Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(authentication.getPrincipal()).thenReturn(member);
        
        ModelAndView model = controller.show(null);
        assertEquals(member, model.getModel().get("member"));
        assertEquals("profile", model.getViewName());
        
        
	}
	@Test
	public void testShowOpenProfile(){
		ModelAndView model = controller.showOpenProfile(1L);
		assertEquals("openprofile", model.getViewName());
		assertEquals(member, model.getModel().get("member"));
		
		//when memberService returns null
		model = controller.showOpenProfile(1L);
		assertEquals(null, model.getModel().get("member"));
	}
//	@Test
//	public void testIsLoggedIn(){
//		//Mock the SecurityContext and Authentication
//		Authentication authentication = Mockito.mock(Authentication.class);
//		when(authentication.getName()).thenReturn(USERNAME);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//		ProfileController controller = new ProfileController();
//		Member member = mock(Member.class);
//		when(member.getUsername()).thenReturn(USERNAME);
//		assertTrue(controller.isLoggedIn(member));
//		
//		assertFalse(controller.isLoggedIn(null));
//		
//		when(member.getUsername()).thenReturn("notTheUsername");
//		assertFalse(controller.isLoggedIn(member));
//	}
	
	
	
	
}
