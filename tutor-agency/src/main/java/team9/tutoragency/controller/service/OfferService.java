package team9.tutoragency.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.TutoringOffer;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.OfferDao;

@Service
public class OfferService {
	
	@Autowired OfferDao offerDao;
	@Autowired CourseDao courseDao;
	
	@Transactional
	public boolean removeOffer(Member member, Long courseId){
		assert courseId!=null;
		
		Course course = courseDao.findOne(courseId);
		
		if(course!= null && member!= null){
			List<TutoringOffer> offers = offerDao.findByMemberAndCourse(member, course);
			for(TutoringOffer offer: offers){
				offerDao.delete(offer);
			}
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean addOffer(Member member, Long courseId, float grade){
		assert courseId!=null;
		
		Course course = courseDao.findOne(courseId);
		
		if(course!= null && member!= null){
			offerDao.save(new TutoringOffer(member, course, grade));
			return true;
		}
		return false;
	}
	/**
	 * Calculates grade/4 and returns a string representation of the double result value.
	 * Used to store the grade as int value (i.e 16 -> "4.0", 17->"4.25").
	 * @param grade
	 * @return
	 */
	public String gradeToString(int grade){
		Double result = (double) grade/4;
		return result.toString();
	}
	
	/**
	 * Returns the gradeString as double multiplied with 4. (i.e. "4.25"->17, "4.0"->16).
	 * @param gradeString, has to be a representation of a floating point value(e.g "4.0")
	 * @return
	 */
	public int stringToGrade(String gradeString) throws NumberFormatException{
		double result = Double.parseDouble(gradeString);
		return (int) (result*4);
		
	}
}

