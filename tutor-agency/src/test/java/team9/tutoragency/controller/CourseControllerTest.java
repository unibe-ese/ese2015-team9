
package team9.tutoragency.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.controller.service.CourseService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test of the class {@link CourseController}. {@link CourseService} provides
 * underlying functionality of {@link CourseController} and is tested separately 
 * in {@link CourseServiceTest}.
 * 
 * @author curtys
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/config/springMVC.xml",
"file:src/main/webapp/WEB-INF/config/springData.xml" })
@Transactional
@Rollback
public class CourseControllerTest {
    
    @Mock
    private CourseService service;
    @InjectMocks
    private CourseController controller;
    private final Member member = new Member();
    
    @Autowired private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    private TestContextManager testContextManager;
    
    @Before
    public void setUp() throws Exception {
        //this allows multiple runners, i.e. MockitoJUnitRunner and SpringJUnit4Runner
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        
        // Mocks the security context and returns our member as authenticated user.
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(authentication.getPrincipal()).thenReturn(member);
    }

    /**
     * Test of showAddCourseView method, of class {@link CourseController}.
     */
    @Test
    public void testShowAddCourseView() throws Exception {
        HttpServletResponse response = null;
//        ModelAndView expResult = new ModelAndView("addCourse");
        ModelAndView result = controller.showAddCourseView(response);
//        assertEquals(expResult.getViewName(), result.getViewName());
        assertViewName(result, "addCourse");
        this.mockMvc.perform(get("/addCourse"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("universities"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().attributeExists("member"));
    }

    /**
     * Test of updateDropdown method, of class {@link CourseController}.
     */
    @Test
    public void testUpdateDropdown() throws Exception {
        AddCourseForm addCourseForm = new AddCourseForm();
        BindingResult bResult = null;
        RedirectAttributes redirectAttributes = null;
//        ModelAndView expResult = new ModelAndView("addCourse");
        ModelAndView result = controller.updateDropdown(addCourseForm, bResult, redirectAttributes);
//        assertEquals(expResult.getViewName(), result.getViewName());
        assertViewName(result, "addCourse");
    }

    /**
     * Test of showCourses method, of class {@link CourseController}.
     */
    @Test
    public void testShowCourses() throws Exception {
        HttpServletResponse response = null;
        ModelAndView expResult = new ModelAndView("showCourses");
        expResult.addObject("courses", new ArrayList<Course>());
        expResult.addObject("unis", null);
        expResult.addObject("member", member);
        ModelAndView result = controller.showCourses(response);
        assertEquals(expResult.getViewName(), result.getViewName());
        assertEquals(expResult.getModelMap(), result.getModelMap());
    }
    
}