package team9.tutoragency.controller.service;

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
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class CourseService {

	@Autowired
	CourseDao courseDao;
	@Autowired
	UniversityDao uniDao;
	@Autowired
	MemberDao memberDao;

	@Transactional
	public void deleteProvidedCourse(Member member, Long courseId) {
		List<Course> courseList = member.getCourseList();

		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getId() == courseId) {
				courseList.remove(courseList.get(i));
			}
		}
        member.setCourseList(courseList);
        memberDao.save(member);
	}

	@Transactional
	public void addCourseToMember(Member member, String courseName) {
		List<Course> courseList = member.getCourseList();
		if (courseList != null) {
			Course course = courseDao.findByName(courseName).get(0);
			courseList.add(course);
			memberDao.save(member);
		}
	}

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
	}
}
