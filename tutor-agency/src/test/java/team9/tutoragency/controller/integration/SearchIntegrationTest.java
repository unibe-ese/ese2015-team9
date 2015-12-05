package team9.tutoragency.controller.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.UniversityDao;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class performs an Integration test for the {@code SearchController.class} and {@code SearchServiceImpl.class}.
 * All possible filter settings are tested separately. 
 * The results are compared to the persistent data, which should be created automatically from EseDB-TestData.sql. 
 * The data itself is first verified in the {@link #setUp()} method. 
 * @see {@linkplain SearchController} , {@linkplain SearchService}
 * @author bruno
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/test/resources/test_springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
public class SearchIntegrationTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Autowired MemberDao memberDao;
	@Autowired UniversityDao uniDao;
	@Autowired CourseDao courseDao;
	@Autowired OfferDao offerDao;
	
	private Member bob, eve;
	private University uniBern, uniLuzern;
	private Course dbBern, dsBern, dbLuzern;
	private Offer bobDbBern, bobDsBern, eveDbLuzern;
	
	private static final String URL = "/search"; // @RequestMapping value

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		bob = memberDao.findOne(1L);
		assertEquals("bob", bob.getUsername());
		assertEquals(40D, bob.getFee(), 1);
		
		eve = memberDao.findOne(2L);
		assertEquals("eve", eve.getUsername());
		assertEquals(30D, eve.getFee(), 1);
		
		uniBern = uniDao.findOne(1L);
		assertEquals("Bern", uniBern.getName());
		
		uniLuzern = uniDao.findOne(2L);
		assertEquals("Luzern", uniLuzern.getName());
		
		dbBern = courseDao.findOne(1L);
		assertEquals("Databases", dbBern.getName());
		assertEquals(uniBern, dbBern.getUniversity());
		
		dsBern = courseDao.findOne(2L);
		assertEquals("Datastructures", dsBern.getName());
		assertEquals(uniBern, dsBern.getUniversity());
		
		dbLuzern = courseDao.findOne(3L);
		assertEquals("Databases", dbLuzern.getName());
		assertEquals(uniLuzern, dbLuzern.getUniversity());
		
		bobDbBern = offerDao.findOne(1L);
		assertEquals(dbBern, bobDbBern.getCourse());
		assertEquals(bob, bobDbBern.getTutor());
		
		bobDsBern = offerDao.findOne(2L);
		assertEquals(dsBern, bobDsBern.getCourse());
		assertEquals(bob, bobDsBern.getTutor());
		
		eveDbLuzern = offerDao.findOne(3L);
		assertEquals(dbLuzern, eveDbLuzern.getCourse());
		assertEquals(eve, eveDbLuzern.getTutor());
	}
	
	@Test
	public void test_getSearchPage() throws Exception{
		mockMvc.perform(get(URL)).andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/searchPage.jsp"))
				.andExpect(model().attribute("form", new SearchForm()))
				.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
				.andExpect(model().attribute("grades", Offer.possibleGrades()));
	}
	
	@Test
	public void test_searchNotFiltered_noSearchText() throws Exception{
		mockMvc.perform(post(URL)).andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/searchPage.jsp"))
				.andExpect(model().attribute("form", new SearchForm()))
				.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
				.andExpect(model().attribute("grades", Offer.possibleGrades()))
				.andExpect(model().attribute("offers", asList(bobDbBern, bobDsBern, eveDbLuzern)));
	}
	
	/**
	 * Performs a search with {@code searchText = "data"} as if a form was sent without filter options.
	 * All offers are expected.
	 * @throws Exception
	 */
	@Test
	public void test_searchNotFiltered_Data() throws Exception{
		String searchText = "data";
		
		mockMvc.perform(post(URL).param("searchText", searchText)).andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", new SearchForm(searchText)))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(bobDbBern, bobDsBern, eveDbLuzern)));
	}
	
	/**
	 * Performs a search with {@code searchText = "datastructures"} as if a form was sent without filter options.
	 * @throws Exception
	 */
	@Test
	public void test_searchNotFiltered_Datastructures() throws Exception{
		String searchText = "datastructures";
		
		mockMvc.perform(post(URL).param("searchText", searchText)).andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", new SearchForm(searchText)))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(bobDsBern)));
	}
	
	/**
	 * Performs a search with {@code searchText = "data"},
	 * and only {@code uniBern} selected.
	 * The {@code Offer} {@code eveDbLuzern} is expected.
	 * @throws Exception
	 */
	@Test
	public void test_searchByUniversity() throws Exception{
		String searchText = "data";
		SearchForm form = new SearchForm(searchText);
		form.setFiltered(true);
		form.setUniversityNames(asList("Luzern"));
		
		mockMvc.perform(post(URL)
				.param("searchText", searchText)
				.param("filtered", "true")
				.param("universityNames", "Luzern")).andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", form))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(eveDbLuzern)));
	}
	
	/**
	 * Performs a search with {@code searchText = "data"},
	 * and {@code minFee = 31}.
	 * The {@code Offer}'s {@code bobDbBern, bobDsBern} are expected.
	 * @throws Exception
	 */
	@Test
	public void test_searchByMinFee() throws Exception{
		String searchText = "data";
		
		SearchForm form = new SearchForm(searchText);
		form.setFiltered(true);
		form.setMinFee(31);
		form.setUniversityNames(asList("Bern", "Luzern"));
		
		mockMvc.perform(post(URL)
				.param("searchText", searchText)
				.param("filtered", "true")
				.param("minFee", "31")
				.param("universityNames", "Bern", "Luzern")).andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", form))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(bobDbBern, bobDsBern)));
	}
	
	/**
	 * Performs a search with {@code searchText = "data"},
	 * and {@code maxFee = 35}.
	 * The {@code Offer} {@code eveDBLuzern} is expected.
	 * @throws Exception
	 */
	@Test
	public void test_searchByMaxFee() throws Exception{
		String searchText = "data";
		
		SearchForm form = new SearchForm(searchText);
		form.setFiltered(true);
		form.setMaxFee(35);
		form.setUniversityNames(asList("Bern", "Luzern"));
		
		mockMvc.perform(post(URL)
				.param("searchText", searchText)
				.param("filtered", "true")
				.param("maxFee", "35")
				.param("universityNames", "Bern", "Luzern")).andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", form))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(eveDbLuzern)));
	}
	
	/**
	 * Performs a search with {@code searchText = ""},
	 * and {@code minGrade = "5.0"}.
	 * The {@code Offer}'s {@code bobDsBern, eveDbLuzern} are expected.
	 * @throws Exception
	 */
	@Test
	public void test_searchByMinGrade() throws Exception{
		String searchText = "";
		
		SearchForm form = new SearchForm(searchText);
		form.setFiltered(true);
		form.setMinGrade("5.0");
		form.setUniversityNames(asList("Bern", "Luzern"));
		
		mockMvc.perform(post(URL)
				.param("searchText", searchText)
				.param("filtered", "true")
				.param("minGrade", "5.0")
				.param("universityNames", "Bern", "Luzern"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/pages/searchPage.jsp"))
		.andExpect(model().attribute("form", form))
		.andExpect(model().attribute("universities", asList(uniBern, uniLuzern)))
		.andExpect(model().attribute("grades", Offer.possibleGrades()))
		.andExpect(model().attribute("offers", asList(bobDsBern, eveDbLuzern)));
	}
}
