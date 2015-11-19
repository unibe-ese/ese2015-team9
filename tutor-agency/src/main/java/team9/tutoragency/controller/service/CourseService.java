package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class CourseService {

	@Autowired
	CourseDao courseDao;
	@Autowired
	UniversityDao uniDao;
	@Autowired
	MemberDao memberDao;

	@Autowired
	OfferDao offerDao;

	@Transactional
	public void deleteProvidedCourse(Member member, Long courseId) {
		List<Offer> offerList = Lists.newArrayList(member.getOffer());
		Offer deleteOffer = null;
		for (int i = 0; i < offerList.size(); i++) {
			deleteOffer = offerList.get(i);
			if (deleteOffer.getCourse().getId() == courseId) {
				member.getOffer().remove(offerList.get(i));
				List<Course> courseList = member.getCourseList();
				courseList.remove(offerList.get(i).getCourse());
				member.setCourseList(courseList);
			}
		}
		memberDao.save(member);
		if (deleteOffer != null) {
			offerDao.delete(deleteOffer);
		}
	}

	/**
	 * Adds a course to a {@link Member} only if the member doesn't have already
	 * an offered {@link Course}.
	 * 
	 * @param member
	 *            The member which wants to offer a new course
	 * @param courseId
	 *            of the course to be added
	 */
	@Transactional
	public void addCourseToMember(Member member, long courseId, float grade) {

		Course course = courseDao.findById(courseId).get(0);

		Offer offer = new Offer(member, course, grade);
		member.getOffer().add(offer);
		member.getCourseList().add(course);
		memberDao.save(member);
		offerDao.save(offer);

	}

	/**
	 * Updates the model for the addCourse view in a workaround fashion. The
	 * selected {@link University} from the {@link AddCourseForm} is removed
	 * from the the list and added to the top in order that the selection in the
	 * addCourse view displays the selected university at the top. The "courses"
	 * in the model contain only the ones belonging to the specified
	 * {@link University}.
	 * 
	 * @param model
	 *            which should be displayed after the update
	 * @param addCourseForm
	 *            the form which has the selected university
	 */
	@Transactional
	public void updateDropdown(ModelAndView model, AddCourseForm addCourseForm) {
		model.addObject("addCourseForm", addCourseForm);
		List<University> universities = Lists.newArrayList(uniDao.findAll());
		University selectedUni = uniDao.findByName(addCourseForm.getSelectedUniversity()).get(0);
		universities.remove(selectedUni);
		universities.add(0, selectedUni); // set chosen university at beginning
											// of list
		model.addObject("universities", universities);
		model.addObject("courses", courseDao.findByUniversity(selectedUni));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		model.addObject("member", member);
		model.addObject("unis", member.getUniversityList());

		model.addObject("gradeChoices", getPossibleGrades());
	}

	private List<String> getPossibleGrades() {
		List<String> gradeChoices = new ArrayList<String>();
		for (float i = 4; i <= 6; i += 0.25) {
			gradeChoices.add(Float.toString(i));
		}
		return gradeChoices;
	}

	@Transactional
	public void generateAddCourseModel(ModelAndView addCourse) {
		List<University> universities = Lists.newArrayList(uniDao.findAll());
		addCourse.addObject("universities", universities);
		addCourse.addObject("courses", courseDao.findByUniversity(universities.get(0)));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		addCourse.addObject("member", member);
		addCourse.addObject("unis", member.getUniversityList());
		addCourse.addObject("gradeChoices", getPossibleGrades());
	}

}
