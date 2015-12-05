package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.UniversityDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static java.util.Arrays.asList;

@RunWith(MockitoJUnitRunner.class)
public class BasicDataServiceTest {

	private University uni1, uni2;
    private Course course1;
    
	@Mock
    private UniversityDao uniDao;
	@Mock 
	private CourseDao courseDao;
    @InjectMocks
    private BasicDataServiceImpl service;
    
    @Before
    public void setUp(){
    	uni1 = new University(1L, "uni1");
    	uni2 = new University(1L, "uni2");
    	course1 = new Course(1L, "course", uni1);
    }
    @Test
    public void test_FindAllUniversities() {
    	when(uniDao.findAll()).thenReturn(asList(uni1, uni2));
        List<University> result = service.findAllUniversites();
        assertEquals(asList(uni1, uni2), result);
    }

	@Test
	public void test_findUniversity(){
		when(uniDao.findOne(1L)).thenReturn(uni1);
		assertEquals(uniDao.findOne(1L), uni1);
	}

	 @Test
	    public void test_FindByNames() {
	        when(uniDao.findByNameIn(asList("uni1", "uni2"))).thenReturn(asList(uni1, uni2));
	        
	        List<String> names = null;
	        List<University> expResult = new ArrayList<>();
	        List<University> result = service.findUniversitiesByNames(names);
	        assertEquals(expResult, result);
	        names = new ArrayList<>();
	        result = service.findUniversitiesByNames(names);
	        assertEquals(expResult, result);
	            
	        result = service.findUniversitiesByNames(asList("uni1", "uni2"));
	        assertEquals(asList(uni1, uni2), result);
	    }

	 @Test
	    public void test_FindAllUniversityNames() {
		 	when(uniDao.findAll()).thenReturn(asList(uni1, uni2));
	        List<String> result = service.findAllUniversityNames();
	        assertEquals(asList("uni1", "uni2"), result);
	    }

	 /**
	  * Tests method {@link BasicDataService#findCourseByUniversity}.
	  */
	@Test
	public void test_findCourseByUniversity(){
		when(courseDao.findByUniversity(uni1)).thenReturn(asList(course1));
		when(courseDao.findByUniversity(uni2)).thenReturn(new ArrayList<Course>());
		
		assertEquals(asList(course1), service.findCoursesByUniversity(uni1));
		assertEquals(new ArrayList<Course>(), service.findCoursesByUniversity(uni2));
	}

	@Test
	public void findCourse(){
		
	}
}
