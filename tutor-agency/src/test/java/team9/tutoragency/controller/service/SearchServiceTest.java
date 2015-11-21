package team9.tutoragency.controller.service;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import team9.tutoragency.controller.pojos.SearchForm;
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
	UniversityService uniService;

	@InjectMocks
	private SearchService searchService;

	private University uni1,uni2;
	private Member member1, member2;
	private Course course1, course2;
	private Offer offer1c1, offer2c1, offer1c2;

	@Before
	public void doSetup() {
		uni1 = new University(1L, "uni1");
		uni2 = new University(2L, "uni2");
		
		member1 = new Member();
		member1.setFee(20D);
		member2 = new Member();
		member2.setFee(30D);
		
		course1 = new Course(1L, "course1", uni1);
		offer1c1 = new Offer(member1, course1, 4.5f);
		offer2c1 = new Offer(member2, course1, 5.2f);
		course1.setOffers(new HashSet<Offer>(asList(offer1c1, offer2c1)));
			
		course2 = new Course(2L, "course2", uni2);
		offer1c2 = new Offer(member1, course2, 4.3f);
		course2.setOffers(new HashSet<Offer>(asList(offer1c2)));
	}

	@Test
	public void testFindOffers() {
		
		assertTrue(false);
		
		/*
		 * TODO needs new return values for the dao mocks
		 */
		
//		/*
//		 * search form with filtered==false should ignore all filter fields and
//		 * return just the answer from the courseDao wrapped as search
//		 */
//		
//		SearchForm form = new SearchForm("course", 0, 20, asList("uni1"));
//		when(courseDao.findByNameContainingIgnoreCase("course")).thenReturn(asList(course1, course2));
//		List<Offer> expected = asList(offer1c1, offer2c1);
//		
//
//		assertEquals(expected, searchService.findOffers(form));
//
//		
//		form.setFiltered(true); 
//		//test fee maximum
//		when(uniService.findByNames(asList("uni1"))).thenReturn(asList(uni1));
//		when(courseDao.findByNameContainingAndUniversity("course", uni1)).thenReturn(asList(course1));
//		expected = asList(offer1c1);
//		assertEquals(expected, searchService.findOffers(form));
//		
//		//Test fee minimum
//		form.setMinFee(21);
//		form.setMaxFee(30);
//		expected = asList(offer2c1);
//		assertEquals(expected, searchService.findOffers(form));
//		
//		//test university
//		form.setUniversityNames(asList("uni1", "uni2"));
//		when(uniService.findByNames(asList("uni1", "uni2"))).thenReturn(asList(uni1, uni2));
//		when(courseDao.findByNameContainingAndUniversity("course", uni2)).thenReturn(asList(course2));
//		expected = asList(offer2c1);
//		assertEquals(expected, searchService.findOffers(form));
		
	}
}
