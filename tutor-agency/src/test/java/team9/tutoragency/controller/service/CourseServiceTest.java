
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

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    
	@Mock
	private CourseDao courseDao;
    @InjectMocks
    private CourseService service;
    @Captor
    private ArgumentCaptor<Member> captor;
    @Captor
    private ArgumentCaptor<String> stringCaptor;
    
    private final List<Course> course1List = new ArrayList<>();
    private final List<Course> coursesAll = new ArrayList<>();
    private final List<Course> course2List = new ArrayList<>();
    private final University uni1 = new University();
    private final University uni2 = new University();
    private final List<University> universitiesAll = new ArrayList<>();
    private final Member member = new Member();
    
    
    @Before
    public void setUp() {
        
        uni1.setId(Long.valueOf(1));
        uni1.setName("uni1");
        uni2.setId(Long.valueOf(2));
        uni2.setName("uni2");
        
        universitiesAll.add(uni1);
        universitiesAll.add(uni2);
        List<University> university2List = new ArrayList<>();
        university2List.add(uni2);
        
        Course course1 = new Course();
        Course course2 = new Course();
        course1.setId(Long.valueOf(1));
        course1.setName("course1");
        course1.setUniversity(uni1);
        course2.setId(Long.valueOf(2));
        course2.setName("course2");
        course2.setUniversity(uni2);     
        coursesAll.add(course1);
        coursesAll.add(course2);
        course1List.add(course1);
        course2List.add(course2);    
    }

    /**
     * Test of findByUniversity method, of class {@link CourseService}.
     */
    @Test
    public void testFindByUniversity() {
        
        Mockito.when(courseDao.findByUniversity(uni1)).thenReturn(course1List);
        
        University university = uni1;
        List<Course> expResult = course1List;
        List<Course> result = service.findByUniversity(university);
        assertEquals(expResult, result);
    }

    /**
     * Test of findByNameContaining method, of class {@link CourseService}.
     */
    @Test
    public void testFindByNameContaining() {
        
        Mockito.when(courseDao.findByNameContainingIgnoreCase(stringCaptor.capture()))
                .thenReturn(course1List);
        List<Course> result = service.findByNameContaining(null);
        assertEquals("", stringCaptor.getValue());
        
        Mockito.when(courseDao.findByNameContainingIgnoreCase("co")).thenReturn(coursesAll);
        result = service.findByNameContaining("co");
        assertEquals(coursesAll, result);
    }

    /**
     * Test of findByNameAndUniversities method, of class {@link CourseService}.
     */
    @Test
    public void testFindByNameAndUniversities() {
        Mockito.when(courseDao.findByNameContainingIgnoreCase(stringCaptor.capture()))
                .thenReturn(coursesAll);
        service.findByNameAndUniversities(null, null);
        assertEquals("", stringCaptor.getValue());
        service.findByNameAndUniversities(null, new ArrayList<University>());
        assertEquals("", stringCaptor.getValue());
        List<Course> result = service.findByNameAndUniversities("co", new ArrayList<University>());
        assertEquals(coursesAll, result);
        
        Mockito.when(courseDao
                .findByNameContainingIgnoreCaseAndUniversityIn("course1", universitiesAll))
                .thenReturn(course1List);
        result = service.findByNameAndUniversities("course1", universitiesAll);
        assertEquals(course1List, result);
    }

    /**
     * Test of findUniversityForCourse method, of class {@link CourseService}.
     */
    @Test
    public void testFindUniversityForCourse() {
        Mockito.when(courseDao.findOne(anyLong())).thenReturn(course1List.get(0));
        long selectedCourse = Long.MIN_VALUE;
        University result = service.findUniversityForCourse(selectedCourse);
        assertEquals(uni1, result);
        selectedCourse = Long.MAX_VALUE;
        result = service.findUniversityForCourse(selectedCourse);
        assertEquals(uni1, result);
        
        selectedCourse = 2L;
        Mockito.when(courseDao.findOne(selectedCourse)).thenReturn(course2List.get(0));
        result = service.findUniversityForCourse(selectedCourse);
        assertEquals(uni2, result);

    } 
    
}
