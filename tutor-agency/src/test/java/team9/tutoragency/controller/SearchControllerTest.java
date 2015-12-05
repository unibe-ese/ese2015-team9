package team9.tutoragency.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

	@Mock
	SearchService searchService;
	@Mock
	BasicDataService dataService;

	@InjectMocks
	SearchController controller = new SearchController();

	private MockMvc mockMvc;

	private University uni1, uni2;
	private Member member1, member2;
	private Course course1, course2;
	private Offer offer1, offer2;

	@Before
	public void setUp() {
		/*
		 * To avoid some "circular-view path" exception. The standard
		 * view-resolver sees a circle, because "search" is forwarded to
		 * "search". So we resolve the forwarded view as search.jsp to avoid the
		 * circle.
		 */
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysExpect(status().isOk())
				.setViewResolvers(viewResolver).build();

		uni1 = new University(1L, "uni1");
		uni2 = new University(2L, "uni2");
		member1 = new Member(1L, "member1");
		member2 = new Member(2L, "member2");
		course1 = new Course(1L, "course1", uni1);
		course2 = new Course(2L, "course2", uni2);
		offer1 = new Offer(member1, course1, 4.0F);
		offer2 = new Offer(member2, course2, 6.0F);

		when(searchService.findOffers(any(SearchForm.class))).thenReturn(asList(offer1, offer2));
		when(dataService.findAllUniversityNames()).thenReturn(asList("uni1", "uni2"));
	}

	@Test
	public void testSearchGetRequest_TextNull() throws Exception {

		mockMvc.perform(get("/search")).andExpect(forwardedUrl("search.jsp"))
				.andExpect(model().attribute("offers", new ArrayList<Offer>()))
				.andExpect(model().attribute("universities", asList("uni1", "uni2")))
				.andExpect(model().attribute("grades", Offer.grades()))
				.andExpect(model().attribute("form", new SearchForm(null)));
		
	}

	@Test
	public void testSearchGetRequest() throws Exception {
		
		mockMvc.perform(get("/search").param("text", "course")).andExpect(forwardedUrl("search.jsp"))
		.andExpect(model().attribute("offers", asList(offer1, offer2)))
		.andExpect(model().attribute("universities", asList("uni1", "uni2")))
		.andExpect(model().attribute("grades", Offer.grades()))
		.andExpect(model().attribute("form", new SearchForm("course")));
	}

	@Test
	public void testSearchPostRequest() throws Exception{

		mockMvc.perform(post("/search")).andExpect(forwardedUrl("search.jsp"))
		.andExpect(model().attribute("offers", asList(offer1, offer2)))
		.andExpect(model().attribute("universities", asList("uni1", "uni2")))
		.andExpect(model().attribute("grades", Offer.grades()))
		.andExpect(model().attribute("form", new SearchForm()));
		
	}
	
}
