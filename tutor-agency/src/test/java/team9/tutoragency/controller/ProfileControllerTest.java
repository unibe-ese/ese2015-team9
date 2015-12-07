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

import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
		when(memberService.findById(any(Long.class))).thenReturn(Optional.of(member), Optional.ofNullable((Member) null));
	}
	
	@Test
	public void testShowOpenProfile(){
		ModelAndView model = controller.showPublicProfile(1L);
		assertEquals("publicProfile", model.getViewName());
		assertEquals(Optional.of(member), model.getModel().get("member"));
		
		//when accountService returns null
		model = controller.showPublicProfile(1L);
		assertEquals(null, model.getModel().get("member"));
		assertEquals("redirect:/", model.getViewName());
	}

	
	
	
	
}
