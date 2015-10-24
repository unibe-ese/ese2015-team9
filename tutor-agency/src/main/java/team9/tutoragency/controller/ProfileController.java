package team9.tutoragency.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.exceptions.IllegalDatabaseStateException;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Controller
public class ProfileController {

	@Autowired
	MemberDao memberDao;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView register(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if (username == null) {
			return new ModelAndView("not-logged-in");
		}
		List<Member> members = memberDao.findByUsername(username);
		
		if (members.size() == 0) {
			return new ModelAndView("not-logged-in");
		}

		if (members.size() > 1) {
			throw new IllegalDatabaseStateException("More than one user registered with the username " + username);
		}
		profile.addObject("member", members.get(0));

		return profile;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView becomeTutor(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if (username == null) {
			return new ModelAndView("not-logged-in");
		}
		List<Member> members = memberDao.findByUsername(username);
		
		if (members.size() == 0) {
			return new ModelAndView("not-logged-in");
		}

		if (members.size() > 1) {
			throw new IllegalDatabaseStateException("More than one user registered with the username " + username);
		}
		Member member = members.get(0);
		member.setIsTutor(true);
		profile.addObject("member", members.get(0));
		return profile;
	}
}
