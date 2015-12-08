package team9.tutoragency.controller;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.class)
public class OfferControllerUnitTest {

	@Mock MemberService memberService;
	@Mock AgencyService agencyService;
	
	@InjectMocks OfferController controller = new OfferController();
	
	
	private Member member;
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
