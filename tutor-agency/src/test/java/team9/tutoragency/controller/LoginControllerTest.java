package team9.tutoragency.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

public class LoginControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
	}

	/**
	 * Tests all the methods in the {@link LoginController}, i.e. test if
	 * the correct view is returned and if the correct (error) message is in the
	 * model.
	 */
	@Test
	public void testReturnedModels() {
		LoginController controller = new LoginController();
		ModelAndView model;

		model = controller.getLoginPage(false);
		assertEquals("loginpage", model.getViewName());
		assertEquals(" ", model.getModel().get("message"));

		model = controller.getLoginPage(true);
		assertEquals("loginpage", model.getViewName());
		assertEquals(LoginController.loginErrorMessage, model.getModel().get("message"));

		model = controller.getDeniedPage();
		assertEquals("loginpage", model.getViewName());
		assertEquals(LoginController.accessErrorMessage, model.getModel().get("message"));

	}
}