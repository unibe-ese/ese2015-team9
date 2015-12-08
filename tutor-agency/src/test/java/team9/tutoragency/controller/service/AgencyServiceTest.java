package team9.tutoragency.controller.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import team9.tutoragency.controller.service.impl.AgencyServiceImpl;
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

import static java.util.Arrays.asList;

/**
 * Unit test class for {@code AgencyServiceImpl}.
 * @see {@link AgencyService}, {@link AgencyServiceImpl}.
 * @author bruno
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AgencyServiceTest {

	@Mock UniversityDao uniDao;
	@Mock CourseDao courseDao;
	@Mock OfferDao offerDao;
	@Mock MemberDao memberDao;
	@Mock SubscriptionDao subscriptionDao;
	
	@InjectMocks AgencyServiceImpl service;
	
	
	private University uni1, uni2;
    private Course course1, course2;
    private Member tutor, student;
    private Offer offer1;
    private Subscription subscription1;
    
    @Before
    public void setUp(){
    	uni1 = new University(1L, "uni1");
    	uni2 = new University(1L, "uni2");
    	course1 = new Course(1L, "course", uni1);
    	course2 = new Course(2L, "course2", uni2);
    	tutor = new Member(1L, "tutor");
    	student = new Member(2L, "student");
    	offer1 = new Offer(1L, tutor, course1);
    	subscription1 = new Subscription(1L, student, offer1);
    	
    	when(courseDao.findOne(1L)).thenReturn(course1);
    	when(courseDao.exists(1L)).thenReturn(true);
    	
    	when(courseDao.findOne(2L)).thenReturn(course2);
    	when(courseDao.exists(2L)).thenReturn(true);
    	
    	when(offerDao.findOne(1L)).thenReturn(offer1);
    	when(offerDao.exists(1L)).thenReturn(true);
    	
    	when(offerDao.findOne(2L)).thenReturn(null);
    	when(offerDao.exists(2L)).thenReturn(false);
    	
    	when(memberDao.findOne(1L)).thenReturn(tutor);
    	when(memberDao.exists(1L)).thenReturn(true);
    	
    	when(memberDao.findOne(2L)).thenReturn(student);
    	when(memberDao.exists(2L)).thenReturn(true);
    	
    	when(offerDao.findByTutorAndCourse(tutor, course2)).thenReturn(new ArrayList<Offer>());
    	when(subscriptionDao.findByOffer(offer1)).thenReturn(asList(subscription1));
    	when(subscriptionDao.findOne(1L)).thenReturn(subscription1);
    }
    
    /**
     * Unit test for {@link AgencyService#findOffer(Long)}.
     */
    @Test
    public void test_findOffer(){
    	assertEquals(Optional.of(offer1), service.findOffer(1L));
    	assertEquals(Optional.empty(),service.findOffer(2L));
    }
    
    @Test
    public void test_removeOffer(){
    	service.removeOffer(1L);
    	verify(subscriptionDao).delete(asList(subscription1));
    	verify(offerDao).delete(1L);
    }
    
    @Test
    public void test_createOffer(){
    	service.createOffer(1L, 2L, 4f);
    	verify(offerDao).save(new Offer(tutor, course2, 4f));
    }
    
    @Test
    public void test_createSubscription(){
    	when(subscriptionDao.findByMemberAndOffer(student, offer1)).thenReturn(new ArrayList<Subscription>());
    	
    	service.createSubscription(2L, 1L);
    	verify(subscriptionDao).save(new Subscription(student, offer1));
    }
    
    @Test
    public void test_acceptSubscription(){
    	subscription1.setAccepted(true);
    	service.acceptSubscription(1L);
    	verify(subscriptionDao).save(subscription1);
    }
}
