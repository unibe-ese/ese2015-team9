package team9.tutoragency.controller.service;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.impl.SearchServiceImpl;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	CourseDao courseDao;

	@Mock
	BasicDataService dataService;

	@InjectMocks
	private SearchServiceImpl searchServiceImpl;

	private University uni1,uni2;
	private Member member1, member2;
	private Course course1, course2;
	private Offer offerM1C1, offerM2C1, offerM1C2;

	@Captor
    private ArgumentCaptor<String> stringCaptor;
	
	@Before
	public void doSetup() {
		uni1 = new University(1L, "uni1");
		uni2 = new University(2L, "uni2");
		
		member1 = new Member();
		member1.setFee(20D);
		member2 = new Member();
		member2.setFee(30D);
		
		course1 = new Course(1L, "course1", uni1);
		offerM1C1 = new Offer(member1, course1, 4.5f);
		offerM2C1 = new Offer(member2, course1, 5.2f);
		course1.setOffers(new HashSet<Offer>(asList(offerM1C1, offerM2C1)));
			
		course2 = new Course(2L, "course2", uni2);
		offerM1C2 = new Offer(member1, course2, 4.3f);
		course2.setOffers(new HashSet<Offer>(asList(offerM1C2)));
		
		
	}

	@Test(expected = NullPointerException.class )
	public void testFindOffersNullForm() {
		searchServiceImpl.findOffers(null);
	}
	

    /**
     * Test of findByNameAndUniversities method, of class {@link CourseServiceImpl}.
     */
    @Test
    public void testFindByNameAndUniversities() {
        Mockito.when(courseDao.findByNameContainingIgnoreCase(stringCaptor.capture()))
                .thenReturn(asList(course1, course2));
        searchServiceImpl.findCoursesByNameAndUniversities(null, null);
       verify(courseDao, never()).findByNameContainingIgnoreCase(any(String.class));
        searchServiceImpl.findCoursesByNameAndUniversities(null, new ArrayList<University>());
        verify(courseDao, never()).findByNameContainingIgnoreCase(any(String.class));
        List<Course> result = searchServiceImpl.findCoursesByNameAndUniversities("co", new ArrayList<University>());
        verify(courseDao, never()).findByNameContainingIgnoreCase(any(String.class));
        
        Mockito.when(courseDao
                .findByNameContainingIgnoreCaseAndUniversityIn("course1", asList(uni1, uni2)))
                .thenReturn(asList(course1));
        result = searchServiceImpl.findCoursesByNameAndUniversities("course1", asList(uni1, uni2));
        assertEquals(asList(course1), result);
    }
}
