package team9.tutoragency.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static java.util.Arrays.asList;

import org.springframework.security.test.context.support.WithUserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@Rollback
public class AccountControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	MemberDao memberDao;
	@Autowired
	UniversityDao uniDao;

	private MockMvc mockMvc;
	private Member tutor1;

	private static final String URL = "/auth/account"; // @RequestMapping value

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		tutor1 = memberDao.findOne(3L);
	}

	/*
	 * To test the url-interception of spring security. The Member class should
	 * extend org.springframework.security.core.userdetails.User. Then we could
	 * inject a non authorized mock.
	 */
	// @WithMockUser(roles={})
	// public void test_UrlInterception() throws Exception{
	//
	// mockMvc.perform(get(URL)).andExpect(status().isOk())
	// .andExpect(forwardedUrl("/pages/loginPage.jsp"));
	// }

	@Test
	@WithUserDetails("tutor1")
	public void test_showProfile() throws Exception {

		mockMvc.perform(get(URL)).andExpect(forwardedUrl("/pages/profile.jsp")).andExpect(status().isOk())
				.andExpect(model().attribute("member", memberDao.findOne(3L)));
	}

	@Test
	@WithUserDetails("tutor1")
	public void test_getEditPage() throws Exception {

		EditForm form = new EditForm(tutor1);

		mockMvc.perform(get(URL + "/edit")).andExpect(forwardedUrl("/pages/edit.jsp"))
				.andExpect(model().attribute("editForm", form))
				.andExpect(model().attribute("universityChoices", asList("Bern", "Basel", "Zürich")))
				.andExpect(model().attribute("member", tutor1));
	}

	@Test
	@WithUserDetails("tutor1")
	public void test_saveChanges_invalidForm() throws Exception {
		EditForm form = new EditForm();

		form.setEmail("test_mail@mail.com");
		form.setLastName("TestName");
		form.setUsername("Student");

		mockMvc.perform(post(URL + "/save").param("email", "test_mail@mail.com").param("oldPassword", "password")
				.param("lastName", "TestName").param("username", "Student")).andExpect(status().isOk())
				.andExpect(model().attribute("member", tutor1)).andExpect(model().attribute("editForm", form))
				.andExpect(model().attribute("universityChoices", asList("Bern", "Basel", "Zürich")))
				.andExpect(forwardedUrl("/pages/edit.jsp"));
	}

	@Test
	@WithUserDetails("tutor1")
	public void test_saveChanges_validForm() throws Exception {
		EditForm form = new EditForm();

		form.setEmail("test_mail@mail.com");
		form.setLastName("TestName");
		form.setUsername("Student");

		mockMvc.perform(post(URL + "/save").param("email", tutor1.getEmail()).param("oldPassword", "tutor1")
				.param("lastName", tutor1.getLastName()).param("fee", "40").param("username", "tutor1"))
				.andExpect(status().isOk()).andExpect(forwardedUrl("/pages/profile.jsp"));
	}
}
