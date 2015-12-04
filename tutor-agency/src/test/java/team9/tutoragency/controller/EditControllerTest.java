package team9.tutoragency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.UniversityService;
import team9.tutoragency.controller.service.validation.EditFormValidationService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.NestedServletException;

import team9.tutoragency.controller.pojos.EditForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test of the class {@link AccountController}.
 * 
 * @author brn
 * @author curtys
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml" })
@Transactional
@Rollback
public class EditControllerTest {

	private Member member;
	private Member editedMember;
	private University uni1;
	private University uni2;
	private List<University> universities;
	private List<String> uniNames;

	@Mock
	private EditFormValidationService validator;
	@Mock
	private MemberService memberService;
	@Mock
	private UniversityService uniService;

	@InjectMocks
	private AccountController controller;
	@Captor
	private ArgumentCaptor<EditForm> captor;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private TestContextManager testContextManager;

	@Before
	public void setUp() throws Exception {

        //this allows multiple runners, i.e. MockitoJUnitRunner and SpringJUnit4ClassRunner
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        
		//initialize test beans
		member = new Member("firstname", "lastname", "name@mail.com", "username", "password");
        member.setId(Long.valueOf(1));
		editedMember = new Member("firstname2", "lastname2", "name2@mail.com", "username2", "password2");

		uni1 = new University();
		uni2 = new University();
		uni1.setName("name1");
		uni2.setName("name2");
		universities = Arrays.asList(new University[] { uni1, uni2 });
		uniNames = Arrays.asList(new String[] { "name1", "name2" });

		// Mock the SecurityContext and Authentication
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(authentication.isAuthenticated()).thenReturn(true);
		Mockito.when(authentication.getPrincipal()).thenReturn(member);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		Mockito.when(uniService.findAll()).thenReturn(universities);
		// Mockito.doNothing().when(memberService).saveEditChange(any(Member.class),
		// captor.capture());
	}


	private void initSecurityContextNOTAuthenticated() {
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(authentication.isAuthenticated()).thenReturn(false);
		Mockito.when(authentication.getPrincipal()).thenReturn(member);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void testEdit() throws Exception {
		EditForm form = new EditForm(member);
		form.setUniversities(uniNames);

		ModelAndView result = controller.getEditPage();
		assertViewName(result, "edit");

		mockMvc.perform(get("/edit")).andExpect(forwardedUrl("/pages/edit.jsp"))
				.andExpect(model().attributeExists("editForm")).andExpect(model().attribute("member", member))
				.andExpect(model().attributeExists("universityChoices"));

	}

}
