package team9.tutoragency.controller.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class OfferServiceTest {
	
	@Test
	public void testStringToGrade(){
		OfferService service = new OfferService();
		assertEquals(16, service.stringToGrade("4.0"));
		assertEquals(16, service.stringToGrade("4"));
		assertEquals(17, service.stringToGrade("4.25"));
		assertEquals(18, service.stringToGrade("4.5"));
		assertEquals(19, service.stringToGrade("4.75"));
	}
	
	@Test
	public void testGradeToString(){
		OfferService service = new OfferService();
		assertEquals("4.0", service.gradeToString(16));
		assertEquals("4.25", service.gradeToString(17));
		assertEquals("4.5", service.gradeToString(18));
		assertEquals("4.75", service.gradeToString(19));
	}

	
}
