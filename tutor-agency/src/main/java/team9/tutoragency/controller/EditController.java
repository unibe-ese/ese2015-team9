package team9.tutoragency.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Controller
public class EditController {

	@Autowired
	MemberDao memberDao;
	@Autowired
	EditFormValidator validator;
	@Autowired
	UniversityDao uniDao;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletResponse response) throws IOException {
		ModelAndView edit = new ModelAndView("edit");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		EditForm editForm = new EditForm();
		editForm.setFirstName(member.getFirstName());
		editForm.setLastName(member.getLastName());
		editForm.setUsername(member.getUsername());
		editForm.setEmail(member.getEmail());
		editForm.setUsername(member.getUsername());
		if (member.getFee() != null) {
			editForm.setFee(member.getFee().toString());
		} else {
			editForm.setFee("0");
		}
		edit.addObject("editForm", editForm);
		List<University> universities = Lists.newArrayList(uniDao.findAll());
		List<String> universityNames = new ArrayList<String>();
		for (University uni : universities) {
			universityNames.add(uni.getName());
		}

		edit.addObject("universityChoices", universityNames);
		edit.addObject("member", member);
		return edit;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveChange(@Valid EditForm editForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model;
		validator.validate(editForm, result);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		if (!result.hasErrors()) {

			member.setEmail(editForm.getEmail());

			if (editForm.getPassword().length() > 0) {
				member.setPassword(DigestUtils.md5Hex(editForm.getPassword()));
			}
			member.setFirstName(editForm.getFirstName());
			member.setLastName(editForm.getLastName());
			member.setUsername(editForm.getUsername());

			if(member.isIsTutor()){
			double fee = Double.parseDouble(editForm.getFee());
			member.setFee(fee);
			List<University> tmpList = new ArrayList<University>();
			for (int i = 0; i < editForm.getUniversities().size(); i++) {
				List<University> selectedUni = uniDao.findByName(editForm.getUniversities().get(i));
				tmpList.add(selectedUni.get(0));
			}

			member.setUniversityList(tmpList);
			}
			memberDao.save(member);
			model = new ModelAndView("profile");
			model.addObject("member", member);
			model.addObject("unis", member.getUniversityList());

		} else {

			for (ObjectError ob : result.getAllErrors()) {
				System.out.println(ob);
			}
			model = new ModelAndView("edit");
			model.addObject("editForm", editForm);

			List<University> universities = Lists.newArrayList(uniDao.findAll());
			List<String> universityNames = new ArrayList<String>();
			for (University uni : universities) {
				universityNames.add(uni.getName());
			}

			model.addObject("universityChoices", universityNames);
			model.addObject("member", member);

		}
		return model;
	}
}
