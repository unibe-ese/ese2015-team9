package team9.tutoragency.controller;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;

/**
 * Unit test class for {@code SearchController.class}
 * @see {@link SearchController}
 * @author bruno
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchControllerUnitTest {

	@Mock
	SearchService searchService;
	@Mock
	BasicDataService dataService;

	@InjectMocks
	SearchController controller = new SearchController();

	private University uni;
	private Member member;
	private Course course;
	private Offer offer;

	private final static String VIEW_NAME = "searchPage";

	@Before
	public void setUp() {

		uni = new University(1L, "uni");
		member = new Member(1L, "member");
		course = new Course(1L, "course", uni);
		offer = new Offer(member, course, 4.0F);

		when(searchService.findOffers(any(SearchForm.class))).thenReturn(asList(offer));
		when(dataService.findAllUniversities()).thenReturn(asList(uni));
	}

	/**
	 * Tests if the method {@code setFilterOptions} adds the expected
	 * {@code University} - and grade (as {@code String}) options to the model.
	 * 
	 * @see {@linkplain SearchController#setFilterOptions(ModelMap)}.
	 */
	@Test
	public void test_setFilterOptions() {
		ModelMap model = new ModelMap();

		controller.setFilterOptions(model);

		assertEquals(asList(uni), model.get("universities"));
		assertEquals(Offer.possibleGrades(), model.get("grades"));

	}

	/**
	 * Tests if the method {@code getSearchPage} adds a {@code new SearchForm}
	 * to the model, and if the view name is correct.
	 * @see {@linkplain SearchController#getSearchPage()}.
	 */
	@Test
	public void test_getSearchPage() throws Exception {

		ModelAndView model = controller.getSearchPage();

		assertEquals(VIEW_NAME, model.getViewName());
		assertEquals(new SearchForm(), model.getModel().get("form"));
	}

	/**
	 * Tests if the method {@code showSearchResults} adds the expected
	 * {@code SearchForm} and {@code Offer}'s to the model. And if the view name
	 * is correct.
	 * 
	 * @see {@linkplain SearchController#showSearchResults(SearchForm)}.
	 */
	@Test
	public void test_showSearchResults() {
		SearchForm form = new SearchForm("text");

		ModelAndView model = controller.showSearchResults(form);

		assertEquals(VIEW_NAME, model.getViewName());
		assertEquals(form, model.getModel().get("form"));
		assertEquals(asList(offer), model.getModel().get("offers"));

	}
}
