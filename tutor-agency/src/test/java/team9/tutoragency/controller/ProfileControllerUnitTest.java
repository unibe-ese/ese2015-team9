package team9.tutoragency.controller;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.Mockito.any;

/**
 * Unit tests for {@code ProfileController}.
 * {@link ProfileController}.
 * @author curtys
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerUnitTest {
	@Mock
	MemberService memberService;
	@InjectMocks
	ProfileController controller;
	
	public final static Member member = new Member();
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		when(memberService.findById(any(Long.class))).thenReturn(Optional.of(member), Optional.ofNullable((Member) null));
	}
	
	@Test
	public void testShowPublicProfile(){
		ModelAndView model = controller.showPublicProfile(1L);
		assertEquals("publicProfile", model.getViewName());
		assertEquals(Optional.of(member), model.getModel().get("member"));
		
		//when accountService returns null
		model = controller.showPublicProfile(1L);
		assertEquals(null, model.getModel().get("member"));
		assertEquals("redirect:/", model.getViewName());
	}

	
	
	
	
}
