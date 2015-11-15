package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import team9.tutoragency.controller.SearchController;
import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.pojos.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	CourseDao courseDao;

	@Mock
	UniversityAccessService uniService;

	@InjectMocks
	private SearchService searchService;

	private List<Member> list(Member... params) {
		List<Member> list = new ArrayList<Member>();
		for (Member param : params) {
			list.add(param);
		}
		return list;
	}

	private List<University> list(University... params) {
		List<University> list = new ArrayList<University>();
		for (University param : params) {
			list.add(param);
		}
		return list;
	}

	private List<String> list(String... params) {
		List<String> list = new ArrayList<String>();
		for (String param : params) {
			list.add(param);
		}
		return list;
	}

	private List<Course> list(Course... params) {
		List<Course> list = new ArrayList<Course>();
		for (Course param : params) {
			list.add(param);
		}
		return list;
	}

	private List<SearchResult> list(SearchResult... params) {
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (SearchResult param : params) {
			list.add(param);
		}
		return list;
	}

	@Before
	public void doSetup() {

	}

	@Test
	public void testFindByCourseNameWithSearchFilter() {
		University uni1 = new University(1L, "uni1");
		University uni2 = new University(2L, "uni2");
		Member member1 = new Member.Builder().fee(20D).build();
		
		Member member2 = new Member.Builder().fee(30D).build();
		Course course1 = new Course(1L, "course1", uni1, list(member1, member2));
		Course course2 = new Course(2L, "course2", uni2, list(member2));

		/*
		 * search form with filtered==false should ignore all filter fields and
		 * return just the answer from the courseDao wrapped as SearchResults
		 */
		SearchForm form = new SearchForm("course", 0, 20, list("uni1"));
		when(courseDao.findByNameContainingIgnoreCase("course")).thenReturn(list(course1, course2));
		List<SearchResult> expected = list(new SearchResult(course1, course1.getMembers()),
				new SearchResult(course2, course2.getMembers()));
		assertEquals(expected, searchService.findCoursesByNameContaining(form));

		
		form.setFiltered(true); 
		//test fee maximum
		when(uniService.findByNames(list("uni1"))).thenReturn(list(uni1));
		when(courseDao.findByNameContainingAndUniversity("course", uni1)).thenReturn(list(course1));
		expected = list(new SearchResult(course1, list(member1)));
		assertEquals(expected, searchService.findCoursesByNameContaining(form));
		
		//Test fee minimum
		form.setMinFee(21);
		form.setMaxFee(30);
		expected = list(new SearchResult(course1, list(member2)));
		assertEquals(expected, searchService.findCoursesByNameContaining(form));
		
		//test university
		form.setUniversityNames(list("uni1", "uni2"));
		when(uniService.findByNames(list("uni1", "uni2"))).thenReturn(list(uni1, uni2));
		when(courseDao.findByNameContainingAndUniversity("course", uni2)).thenReturn(list(course2));
		expected = list(new SearchResult(course1, list(member2)), new SearchResult(course2, list(member2)));
		assertEquals(expected, searchService.findCoursesByNameContaining(form));
		
	}

	/**
	 * This method tests if findCoursesByNameContaining returns the correct
	 * {@link SearchResult}'s. That is, for each course, returned from {@link CourseDao#findByNameContainingIgnoreCase(String)}, 
	 * a SearchResult which contains the course and all its members.
	 * 
	 */
	@Test
	public void testFindCoursesByNameContaining() {

		University uni1 = new University(1L, "uni1");
		Member member1 = new Member.Builder().fee(20D).build();
		Member member2 = new Member.Builder().fee(30D).build();
		Course course1 = new Course(1L, "course1", uni1, list(member1, member2));
		Mockito.when(courseDao.findByNameContainingIgnoreCase("text")).thenReturn(list(course1));
		
		List<SearchResult> results = searchService.findCoursesByNameContaining("text");

		List<SearchResult> expectedResults = list(new SearchResult(course1, course1.getMembers()));

		assertEquals(expectedResults, results);
	}

}
