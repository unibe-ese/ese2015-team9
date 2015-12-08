package team9.tutoragency.controller.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.SubscriptionDao;
import team9.tutoragency.model.dao.UniversityDao;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.test.context.support.WithUserDetails;

/**
 * This class provides a Integration Test, that simulates the life cycle of an {@code Offer}. 
 * Including creating and accepting a {@code Subscription} for this offer.
 * @author bruno
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/test/resources/test_springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
public class OfferControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	MemberDao memberDao;
	@Autowired
	UniversityDao uniDao;
	@Autowired
	OfferDao offerDao;
	@Autowired
	SubscriptionDao subscriptionDao;
	@Autowired
	CourseDao courseDao;

	private MockMvc mockMvc;
	
	private Member eve;
	private Course dbBern;
	private University uniBern;

	private static final String URL = "/auth/offer/"; // @RequestMapping value

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		uniBern = uniDao.findOne(1L);
		assertEquals("Bern", uniBern.getName());
		
		eve = memberDao.findOne(2L);
		assertEquals("eve", eve.getUsername());
		assertEquals(30D, eve.getFee(), 1);
		
		dbBern = courseDao.findOne(1L);
		assertEquals("Databases", dbBern.getName());
		assertEquals(uniBern, dbBern.getUniversity());

	}

	/**
	 * This test creates an offer, then a subscription for this offer, accepts the subscription. And finally deletes the offer.
	 * If it fails, the offer might not have been removed from your database.
	 * @throws Exception
	 */
	@Test
	@WithUserDetails("eve")
	@Commit
	public void test_offerLifeCycle() throws Exception {
		assertEquals(0, offerDao.findByTutorAndCourse(eve, dbBern).size());
		
		//create offer
		mockMvc.perform(post(URL + "new/")
				.param("memberId", eve.getId().toString())
				.param("courseId", dbBern.getId().toString())
				.param("grade", "4.0"))
				.andExpect(status().is3xxRedirection());
		
		assertEquals(1, offerDao.findByTutorAndCourse(eve, dbBern).size());
		Offer offer = offerDao.findByTutorAndCourse(eve, dbBern).get(0);
		
		//subscribe to offer
		mockMvc.perform(get(URL + offer.getId() + "/subscribe"))
			.andExpect(status().is3xxRedirection());
		
		assertEquals(1, subscriptionDao.findByMemberAndOffer(eve, offer).size());
		Subscription subscription = subscriptionDao.findByMemberAndOffer(eve, offer).get(0);
		
		//accept offer
		mockMvc.perform(get(URL + offer.getId() + "/accept/" + subscription.getId() + "/"))
				.andExpect(status().is3xxRedirection());
		
		subscription = subscriptionDao.findByMemberAndOffer(eve, offer).get(0);
		assertEquals(true, subscription.isAccepted());
		
		//delete offer
		mockMvc.perform(get(URL + offer.getId() + "/delete"));
		assertEquals(0, offerDao.findByTutorAndCourse(eve, dbBern).size());	
	}
	

}
