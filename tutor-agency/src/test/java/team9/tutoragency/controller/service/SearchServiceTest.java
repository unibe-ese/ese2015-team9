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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/test-context.xml" })
public class SearchServiceTest {

	@Autowired
	@Qualifier("memberDaoMock")
	private MemberDao memberDao;

	@Autowired
	@Qualifier("courseDaoMock")
	private CourseDao courseDao;

	@Autowired
	private SearchService searchService;

	private Course course1;
	private Course course2;
	private Member member1;
	private Member member2;

	@Before
	public void doSetup() {
		memberDao = mock(MemberDao.class);
		courseDao = mock(CourseDao.class);

		course1 = new Course();
		course2= new Course();
		course1.setId(1L);
		course2.setId(2L);
		course1.setName("Introduction to Databases");
		course2.setName("Datastructures and Algorithms");

		member1 = new Member();
		member2 = new Member();

		List<Course> courses1 = new ArrayList<Course>();
		courses1.add(course1);

		List<Course> courses2 = new ArrayList<Course>();
		courses2.add(course1);
		courses2.add(course2);

		member1.setCourseList(courses1);
		member2.setCourseList(courses2);
	}

	@Test
	public void testFindCoursesByNameContaining() {

		when(courseDao.findByNameContaining(any(String.class))).then(new Answer<List<Course>>() {
			public List<Course> answer(InvocationOnMock invocation) throws Throwable {
				List<Course> courses = new ArrayList<Course>();
				
			
				courses.add(course1);
				courses.add(course2);
				return courses;

			}
		});

		when(memberDao.findAll()).then(new Answer<List<Member>>() {

			public List<Member> answer(InvocationOnMock invocation) throws Throwable {

				List<Member> members = new ArrayList<Member>();

				members.add(member1);
				members.add(member2);

				return members;
			}
		});

		List<SearchResult> results = searchService.findCoursesByNameContaining("Data");

		List<SearchResult> expectedResults = new ArrayList<SearchResult>();
		expectedResults.add(new SearchResult(course1, 
				Arrays.asList(new Member[]{ member1, member2 })));
		expectedResults.add(new SearchResult(course2, 
				Arrays.asList(new Member[] { member2 })));
		
		assertEquals(expectedResults, results);

	}
}
