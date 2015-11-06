package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import team9.tutoragency.controller.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/test-context.xml" })
public class SearchServiceTest {

	@Autowired
	@Qualifier("memberDaoMock")
	MemberDao memberDao;

	@Autowired
	@Qualifier("courseDaoMock")
	CourseDao courseDao;

	@Autowired
	private SearchService searchService;

	private Course course1;
	private Course course2;
	private Member member1;
	private Member member2;

	private ArrayList<Course> courseList1;
	private ArrayList<Course> courseList2;
	private List<Member> memberList;

	@Before
	public void doSetup() {
		memberDao = mock(MemberDao.class);
		courseDao = mock(CourseDao.class);

		course1 = new Course();
		course2 = new Course();
		course1.setId(1L);
		course2.setId(2L);
		course1.setName("Introduction to Databases");
		course2.setName("Datastructures and Algorithms");

		member1 = new Member();
		member2 = new Member();

		courseList1 = new ArrayList<Course>();
		courseList1.add(course1);

		courseList2 = new ArrayList<Course>();
		courseList2.add(course1);
		courseList2.add(course2);

		member1.setCourseList(courseList1);
		member2.setCourseList(courseList2);
		
		memberList = Arrays.asList(new Member[]{member1,member2});
		
		when(courseDao.findByNameContaining("Data")).thenReturn(courseList2);
		
		 
		when(memberDao.findAll()).thenReturn(memberList);
	}

	@Test
	public void testFindCoursesByNameContaining() {

		

		List<SearchResult> results = searchService.findCoursesByNameContaining("Data");

		List<SearchResult> expectedResults = new ArrayList<SearchResult>();
		expectedResults.add(new SearchResult(course1, Arrays.asList(new Member[] { member1, member2 })));
		expectedResults.add(new SearchResult(course2, Arrays.asList(new Member[] { member2 })));

		assertEquals(expectedResults, results);

	}
}
