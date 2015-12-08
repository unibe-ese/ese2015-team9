package team9.tutoragency.controller;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.impl.AccountServiceImpl;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static java.util.Arrays.asList;


@RunWith(MockitoJUnitRunner.class)
public class OfferControllerUnitTest {

	@Mock MemberService memberService;
	@Mock AgencyService agencyService;
	
	@InjectMocks OfferController controller = new OfferController();
	
	
	private Member member, tutor;
	private Offer offer;
	
	@Before
	public void setUp(){
		member = new Member(1L, "Bob");
		offer = new Offer(1L, member, null);
		when(memberService.getAuthenticatedMember()).thenReturn(Optional.of(member));
		when(agencyService.findOffer(1L)).thenReturn(Optional.of(offer));
		
	}
	
	@Test
	public void test_subscribe(){
		String view = controller.subscribe(1L);
		
		verify(agencyService, times(1)).createSubscription(1L, 1L);
		assertEquals("redirect:/auth/account", view);
	}
	
	@Test
	public void test_acceptSubscription(){
		String view = controller.acceptSubscription(1L, 1L);
		verify(agencyService, times(1)).acceptSubscription(1L);
		assertEquals("redirect:/auth/account#offers", view);
	}
	
	@Test
	public void test_delete() throws IOException{
		String view = controller.deleteCourse(1L);
		verify(agencyService, times(1)).removeOffer(1L);
		assertEquals("redirect:/auth/account", view);
	}
	
}
