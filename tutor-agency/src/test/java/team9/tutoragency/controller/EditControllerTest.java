package team9.tutoragency.controller;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import team9.tutoragency.controller.service.EditFormValidationService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.UniversityAccessService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;

import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.SignupFormValidationService;
import team9.tutoragency.controller.service.SignupFromSaveService;
/**
 * For know this Class test only the Edit method.
 * @author brn
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EditControllerTest {
	private MockMvc mockMvc;
	private Member member ;
	private Member editedMember ;
	private University uni1;
	private University uni2;
	private List<University> universities ;
	private List<String> uniNames;
	
	@Mock
	EditFormValidationService validator;
	@Mock
	MemberService memberService;
	@Mock
	UniversityAccessService uniService;
	
	@InjectMocks
	EditController controller;
	
	
	@Before
	public void setUp() {
		//initialize test beans
		member = new Member("firstname", "lastname", "name@mail.com", "username", "password" );
		editedMember = new Member("firstname2", "lastname2", "name2@mail.com", "username2", "password2" );
		uni1 = new University();
		uni2 = new University();
		uni1.setName("name1");
		uni2.setName("name2");
		universities = Arrays.asList(new University[]{uni1, uni2});
		uniNames = Arrays.asList(new String[]{"name1", "name2"});
		this.mockMvc = MockMvcBuilders.standaloneSetup(new EditController()).alwaysExpect(status().isOk()).build();
	}
	@Test
	public void testExtractNames(){
		List<String> expected = Arrays.asList(new String[]{"name1", "name2"});
		assertEquals(expected, controller.extractNames(universities));
	}
	
	@Test
	public void testEdit(){	
		//Mock the SecurityContext and Authentication
		Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(authentication.getPrincipal()).thenReturn(member);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
      
        when(uniService.findAll()).thenReturn(universities);
        EditForm form = new EditForm(member);
        form.setUniversities(uniNames);
        
        try {
			mockMvc.perform(get("/edit")).andExpect(forwardedUrl("edit")).andExpect(model().attribute("universityChoices", uniNames)).andExpect(model().attribute("editForm", form)).andExpect(model().attribute("member", member));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         
	}
}
