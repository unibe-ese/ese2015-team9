package team9.tutoragency.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import team9.tutoragency.model.Member;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ProfileControllerTest {

	private MockMvc mockMvc;
	public final static String USERNAME = "username";
	@Before
	public void setUp() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
		
		
	}
	
	@Test
	public void testIsLoggedIn(){
		//Mock the SecurityContext and Authentication
		Authentication authentication = Mockito.mock(Authentication.class);
		when(authentication.getName()).thenReturn(USERNAME);
		SecurityContextHolder.getContext().setAuthentication(authentication);
			
		ProfileController controller = new ProfileController();
		Member member = mock(Member.class);
		when(member.getUsername()).thenReturn(USERNAME);
		assertTrue(controller.isLoggedIn(member));
		
		assertFalse(controller.isLoggedIn(null));
		
		when(member.getUsername()).thenReturn("notTheUsername");
		assertFalse(controller.isLoggedIn(member));
	}
	
	
	
	
}
