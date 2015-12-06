package team9.tutoragency.controller.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import team9.tutoragency.controller.pojos.OfferForm;
import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.dao.CourseDao;

@Service
public class OfferFormValidator implements Validator{
	@Autowired AgencyService service;
	@Autowired CourseDao courseDao;
	@Autowired MemberService memberService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OfferForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OfferForm form = (OfferForm) target;
		
		Long memberId = form.getMemberId();
		if (memberId==null) throw new AssertionError("memberId is NULL");
		
		assert memberService.findById(memberId) != null;
		
		Long courseId = form.getCourseId();
		if(courseId == null || courseDao.findOne(courseId) == null)
			errors.rejectValue("courseId", "courseId.notPresent","select a course");
		else{
			if(!service.isNewOffer(memberId, courseId))
				errors.rejectValue("courseId", "courseId.alreadyOffered","You already offer tutoring for this course. Please select another one.");
		}
		
		String gradeAsString = form.getGrade();
		float grade = 0f;
		
		try{
			grade = Float.parseFloat(gradeAsString);
		} catch(NumberFormatException e){
			errors.rejectValue("grade", "select a grade");
		}
		
		assert (grade>=4f && grade<=6f);		
	}

}
