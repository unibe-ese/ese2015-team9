
package team9.tutoragency.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.controller.service.CourseService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.controller.service.UniversityService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import static org.mockito.Mockito.any;
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
	private OfferService offerService;
	@Mock
	private MemberService memberService;
   @Mock
   private UniversityService uniService;
    @Mock
    private CourseService service;
    @InjectMocks
    private CourseController controller;
    
    
    @Autowired private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    private University uni1, uni2;
    private Course course1, course2;
    private List<University> unis;
    private Member member;
    private TestContextManager testContextManager;
    
    @Before
    public void setUp() throws Exception {
    	member = new Member("name", "lname", "uname", "pw", "mail@mail.com");
    	uni1 = new University(1L, "uni1");
    	uni2 = new University(2L, "uni2");
    	unis = Arrays.asList(new University[]{uni1, uni2});
        //this allows multiple runners, i.e. MockitoJUnitRunner and SpringJUnit4Runner
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
//        .webAppContextSetup(this.wac)
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        Mockito.when(authentication.getPrincipal()).thenReturn(member);
        
        Mockito.when(uniService.findAll()).thenReturn(unis);
        Mockito.when(uniService.findByName("uni1")).thenReturn(Arrays.asList(new University[]{uni1}));
        Mockito.when(uniService.findByName("uni2")).thenReturn(Arrays.asList(new University[]{uni2}));
        Mockito.when(offerService.findByTutor(any(Member.class))).thenReturn(null);
        Mockito.when(memberService.getAuthenticatedMember()).thenReturn( Optional.of(member));
        
     
    }

    /**
     * Test of showAddCourseView method, of class {@link CourseController}.
     */
  /*  @Test
    public void testShowAddCourseView() {
        HttpServletResponse response = null;
//        ModelAndView expResult = new ModelAndView("addCourse");
      //  ModelAndView result = controller.showAddCourseView(unis, response);
//        assertEquals(expResult.getViewName(), result.getViewName());
        assertViewName(result, "addCourse");
        boolean exception =false;
        try {
			this.mockMvc.perform(get("/addOffer"))
			        .andExpect(status().isOk())
			        .andExpect(model().attributeExists("universities"))
			        .andExpect(model().attributeExists("courses"))
			        .andExpect(model().attributeExists("member"));
		} catch (Exception e) {
			exception=true;
			e.printStackTrace();
		}
        assertFalse(exception);
    }*/

    

    /**
     * Test of showCourses method, of class {@link CourseController}.
     */
    @Test
    public void testShowCourses() throws Exception {
//        HttpServletResponse response = null;
//        ModelAndView expResult = new ModelAndView("showCourses");
//        expResult.addObject("courses", new ArrayList<Course>());
//        expResult.addObject("unis", null);
//        expResult.addObject("member", member);
//        ModelAndView result = controller.showCourses(response);
//        assertEquals(expResult.getViewName(), result.getViewName());
//        assertEquals(expResult.getModelMap(), result.getModelMap());
    }
    
//    @Test
//    public void testUpdateDropdown() {
//        ModelAndView model = new ModelAndView("addCourse");
//        AddCourseForm addCourseForm = new AddCourseForm();
//        addCourseForm.setSelectedUniversity("uni2");
//        service.updateDropdown(model, addCourseForm);
//        List<University> unisReverse = new ArrayList<University>();
//        unisReverse.add(uni2);
//        unisReverse.add(uni1);
//        ModelMap modelMap = model.getModelMap();
//        
//        assertEquals(member, (Member)modelMap.get("member"));
//        assertEquals(course2List, (List<Course>)modelMap.get("courses"));
//        assertEquals(unisReverse, (List<University>)modelMap.get("universities"));
//        assertEquals(addCourseForm, (AddCourseForm)modelMap.get("addCourseForm"));
//
//    }
//
//    /**
//     * Test of generateAddCourseModel method, of class {@link CourseService}.
//     */
//    @Test
//    public void testGenerateAddCourseModel() {
//        ModelAndView model = new ModelAndView("addCourse");
//        controller.generateAddCourseModel(model);
//        ModelMap modelMap = model.getModelMap();
//        
//        assertEquals(member, (Member)modelMap.get("member"));
//        assertEquals(coursesSingle, (List<Course>)modelMap.get("courses"));
//        assertEquals(universitiesAll, (List<University>)modelMap.get("universities"));
//    }
}