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

import team9.tutoragency.controller.pojos.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	MemberDao memberDao;

	@Mock
	CourseDao courseDao;

	@InjectMocks
	private SearchService searchService;

	private Course course1;
	private Course course2;
	private Course course3;
	private Member member1;
	private Member member2;
	private ArrayList<Course> courseList1;
	private ArrayList<Course> courseList2;
	private ArrayList<Course> courseList3;
	private List<Member> memberList;
	private String searchText;

	@Before
	public void doSetup() {

		course1 = new Course();
		course2 = new Course();
		course3 = new Course();
		
		course1.setId(1L);
		course2.setId(2L);
		course3.setId(3L);
		
		course1.setName("Introduction to Databases");
		course2.setName("Datastructures and Algorithms");
		course3.setName("Statistics");
		
		searchText = "Data";

		courseList1 = new ArrayList<Course>();
		courseList2 = new ArrayList<Course>();
		
		courseList1.add(course1);
		courseList1.add(course3);
		
		courseList2.add(course1);
		courseList2.add(course2);
		
		member1 = new Member();
		member2 = new Member();
		
		member1.setCourseList(courseList1);
		member2.setCourseList(courseList2);

		memberList = Arrays.asList(new Member[] { member1, member2 });

	}

	/**
	 * This method tests if findCoursesByNameContaining returns the correct
	 * {@link SearchResult}'s. That is, for each course, which contains the search
	 * text in his name, a SearchResult, that contains exactly those Members,
	 * which have this course in their courseList. Since the method returns only
	 * the SearchResults obtained from processing of the search text in two
	 * private methods, it's just a black box test for those methods.
	 */
	@Test
	public void testFindCoursesByNameContaining() {

		Mockito.when(courseDao.findByNameContaining(searchText)).thenReturn(courseList2);

		Mockito.when(memberDao.findAll()).thenReturn(memberList);

		List<SearchResult> results = searchService.findCoursesByNameContaining("Data");

		List<SearchResult> expectedResults = new ArrayList<SearchResult>();

		expectedResults.add(new SearchResult(course1, Arrays.asList(new Member[] { member1, member2 })));
		expectedResults.add(new SearchResult(course2, Arrays.asList(new Member[] { member2 })));

		assertEquals(expectedResults, results);
	}
}
