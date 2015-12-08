package team9.tutoragency.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.validation.EditFormValidator;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static java.util.Arrays.asList;
/**
 * Unit tests for the class {@link AccountController}.
 * @author bruno
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerUnitTest {

	private MockMvc mockMvc;

	@Mock
	private EditFormValidator validator;

	@Mock 
	private MemberService memberService;
	@Mock
	private BasicDataService dataService;
	@Mock
	private AccountService accountService;
	@InjectMocks
	private AccountController controller = new AccountController();

	private Member member;

	private static final String URL = "/auth/account"; // @RequestMapping value
														// of the controller.
	@Before
	public void setUp() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();

		member = mock(Member.class);
		when(member.getUniversityList()).thenReturn(asList(new University(1L, "uni1")));

		when(memberService.getAuthenticatedMember()).thenReturn(Optional.of(member));
		when(dataService.findAllUniversityNames()).thenReturn(asList("uniname"));
	}

	@Test
	public void test_showProfile() throws Exception {

		mockMvc.perform(get(URL)).andExpect(forwardedUrl("profile.jsp")).andExpect(status().isOk())
				.andExpect(model().attribute("member", member));
	}

	@Test
	public void test_getEditPage() throws Exception {
		when(dataService.findAllUniversityNames()).thenReturn(asList("uniname"));

		EditForm expectedForm = new EditForm(member);

		mockMvc.perform(get(URL + "/edit")).andExpect(forwardedUrl("edit.jsp")).andExpect(status().isOk())
				.andExpect(model().attribute("universityChoices", asList("uniname")))
				.andExpect(model().attribute("editForm", expectedForm)).andExpect(model().attribute("member", member));
	}

	@Test
	public void test_saveChanges_InvalidForm() throws Exception {
		EditForm form = new EditForm(member);
		
		mockMvc.perform(post(URL + "/save")).andExpect(status().isOk());
		
		ModelAndView expResult = new ModelAndView("edit");
		expResult.addObject("universityChoices", asList("uniname"));
		expResult.addObject("editForm", form);
		expResult.addObject("member", member);
		
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);

		ModelAndView result = controller.saveChange(form, bindingResult);
		
		assertViewName(result, "edit");
		assertEquals(expResult.getModelMap(), result.getModelMap());
	}

	@Test
	public void test_saveChanges_ValidForm() throws IOException{
		EditForm editForm = mock(EditForm.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		ModelAndView result = controller.saveChange(editForm, bindingResult);
		
		assertViewName(result, "profile");
		assertEquals(member, result.getModelMap().get("member"));
		
	}
}
