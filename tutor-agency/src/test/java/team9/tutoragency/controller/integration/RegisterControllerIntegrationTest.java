package team9.tutoragency.controller.integration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.validation.SignupFormValidator;
import util.SignupFormValidatorMock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/config/springMVC.xml",
"file:src/test/resources/test_springData.xml" })
@Transactional
@Rollback
public class RegisterControllerIntegrationTest {
   
    @Autowired private WebApplicationContext wac;
	
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void testCreateMember_formAccepted() throws Exception{ 	
    	mockMvc.perform(post("/create")
    		.param("email", "test_mail@mail.com")
    		.param("password", "password")
    		.param("passwordConfirm", "password")
    		.param("readAGB", "true"))
    		.andExpect(forwardedUrl("/pages/registerSuccess.jsp"));
    }
    
    @Test
    public void testCreateMember_formRejected() throws Exception{ 	
    	SignupForm expectedForm = new SignupForm();
    	expectedForm.setUsername("Bob");
    	expectedForm.setEmail("test_mail@mail.com");
    	expectedForm.setPassword("password");
    	expectedForm.setPasswordConfirm("password");
    	
    	mockMvc.perform(post("/create")
    			.param("username", "Bob")
    			.param("email", "test_mail@mail.com")
        		.param("password", "password")
        		.param("passwordConfirm", "password")
        		.param("readAGB", "false"))
    		.andExpect(status().isOk())
    		.andExpect(forwardedUrl("/pages/signupPage.jsp"))
    		.andExpect(model().attribute("signupForm", expectedForm));
    }
}
