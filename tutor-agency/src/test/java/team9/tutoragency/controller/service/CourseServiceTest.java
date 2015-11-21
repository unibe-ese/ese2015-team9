
package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    
    @Mock
    private MemberDao memberDao;
    @Mock
    private UniversityDao uniDao;
	@Mock
	private CourseDao courseDao;
    @InjectMocks
    private CourseService service;
    @Captor
    private ArgumentCaptor<Member> captor;
    
    private final List<Course> coursesSingle = new ArrayList<Course>();
    private final List<Course> coursesMuliple = new ArrayList<Course>();
    private final List<Course> course2List = new ArrayList<Course>();
    private final University uni1 = new University();
    private final University uni2 = new University();
    private final List<University> universitiesAll = new ArrayList<University>();
    private final Member member = new Member();
    
    
    @Before
    public void setUp() {
        
        uni1.setId(Long.valueOf(1));
        uni1.setName("uni1");
        uni2.setId(Long.valueOf(2));
        uni2.setName("uni2");
        
        universitiesAll.add(uni1);
        universitiesAll.add(uni2);
        List<University> university2List = new ArrayList<University>();
        university2List.add(uni2);
        
        Course course1 = new Course();
        Course course2 = new Course();
        course1.setId(Long.valueOf(1));
        course1.setName("course1");
        course1.setUniversity(uni1);
        course2.setId(Long.valueOf(2));
        course2.setName("course2");
        course2.setUniversity(uni2);     
        coursesMuliple.add(course1);
        coursesMuliple.add(course2);
        coursesSingle.add(course1);
        course2List.add(course2);
        
        Mockito.when(memberDao.save(captor.capture())).thenReturn(any(Member.class));
        Mockito.when(courseDao.findByName("course2")).thenReturn(course2List);
        Mockito.when(courseDao.findByUniversity(uni2)).thenReturn(course2List);
        Mockito.when(courseDao.findByUniversity(uni1)).thenReturn(coursesSingle);
        Mockito.when(uniDao.findAll()).thenReturn(universitiesAll);
        Mockito.when(uniDao.findByName("uni2")).thenReturn(university2List);        
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(authentication.getPrincipal()).thenReturn(member);
        
    }

//    /**
//     * Test of deleteProvidedCourse method, of class {@link CourseService}.
//     */
//    @Test
//    public void testDeleteProvidedCourse() {
//        Long courseId = (long) 2;
//        
//        member.setCourseList(coursesMuliple);
//        
//        service.deleteProvidedCourse(member, courseId);
//        assertEquals(coursesSingle, captor.getValue().getCourseList());
//    }

    /**
     * Test of addCourseToMember method, of class {@link CourseService}.
     */
//    @Test
//    public void testAddCourseToMember() {
//    	long idCourse2 = 2;
//        Mockito.when(courseDao.findById(idCourse2)).thenReturn(course2List);
//        
//        member.setCourseList(coursesSingle);
//        
//        //service.addCourseToMember(member, idCourse2);
//        assertEquals(coursesMuliple, captor.getValue().getCourseList());
//    }

    
    
}
