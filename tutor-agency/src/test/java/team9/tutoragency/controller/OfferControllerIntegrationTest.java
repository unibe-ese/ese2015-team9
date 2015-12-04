package team9.tutoragency.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.SubscriptionDao;
import team9.tutoragency.model.dao.UniversityDao;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static java.util.Arrays.asList;

import org.springframework.security.test.context.support.WithUserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
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
	private Member member;
	private Course course;

	private static final String URL = "/auth/account"; // @RequestMapping value

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		member = memberDao.findOne(1L);
		course = courseDao.findOne(1L);

	}

	/**
	 * This test creates an offer, then a subscription for this offer, accepts the subscription. And finally deletes the offer.
	 * If it fails, the offer might not have been removed from your database.
	 * @throws Exception
	 */
	@Test
	@WithUserDetails("ese")
	@Commit
	public void test_offerLiveCycle() throws Exception {
		assertEquals(0, offerDao.findByTutorAndCourse(member, course).size());
		
		//create offer
		mockMvc.perform(post("/auth/offer/new/")
				.param("memberId", "1")
				.param("courseId", "1")
				.param("grade", "4.0"))
				.andExpect(status().is3xxRedirection());
		
		assertEquals(1, offerDao.findByTutorAndCourse(member, courseDao.findOne(1L)).size());
		Offer offer = offerDao.findByTutorAndCourse(member, courseDao.findOne(1L)).get(0);
		
		//subscribe to offer
		mockMvc.perform(get("/auth/offer/" + offer.getId() + "/subscribe"))
			.andExpect(status().is3xxRedirection());
		
		assertEquals(1, subscriptionDao.findByMemberAndOffer(member, offer).size());
		Subscription subscription = subscriptionDao.findByMemberAndOffer(member, offer).get(0);
		
		//accept offer
		mockMvc.perform(get("/auth/offer/" + offer.getId() + "/accept/" + subscription.getId() + "/"))
				.andExpect(status().is3xxRedirection());
		
		subscription = subscriptionDao.findByMemberAndOffer(member, offer).get(0);
		assertEquals(true, subscription.isAccepted());
		
		//delete offer
		mockMvc.perform(get("/auth/offer/" + offer.getId() + "/delete"));
		assertEquals(0, offerDao.findByTutorAndCourse(memberDao.findOne(1L), courseDao.findOne(1L)).size());	
	}
	

}
