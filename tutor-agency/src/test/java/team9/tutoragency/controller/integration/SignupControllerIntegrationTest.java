package team9.tutoragency.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import team9.tutoragency.controller.pojos.SignupForm;


/**
 * This class provides an Integration Test, simulating the signUp process.
 * @author bruno
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/config/springMVC.xml",
"file:src/test/resources/test_springData.xml" })
@Transactional
@Rollback
public class SignupControllerIntegrationTest {
   
    @Autowired private WebApplicationContext wac;
	
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void testCreateMember_formAccepted() throws Exception{ 	
    	mockMvc.perform(post("/create")
    		.param("username", "Alice")
    		.param("lastName", "A.")
    		.param("firstName", "alice")
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
    	expectedForm.setLastName("B.");
    	expectedForm.setFirstName("Bob");
    	expectedForm.setEmail("test_mail@mail.com");
    	expectedForm.setPassword("password");
    	expectedForm.setPasswordConfirm("password");
    	
    	mockMvc.perform(post("/create")
    			.param("username", "Bob")
    			.param("lastName", "B.")
    			.param("firstName", "Bob")
    			.param("email", "test_mail@mail.com")
        		.param("password", "password")
        		.param("passwordConfirm", "password")
        		.param("readAGB", "false"))
    		.andExpect(status().isOk())
    		.andExpect(forwardedUrl("/pages/signupPage.jsp"))
    		.andExpect(model().attribute("signupForm", expectedForm));
    }
}
