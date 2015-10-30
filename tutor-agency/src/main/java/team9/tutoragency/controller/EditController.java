package team9.tutoragency.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Controller
public class EditController {

	@Autowired
	MemberDao memberDao;
	@Autowired
	EditFormValidator validator;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveChange(@Valid EditForm editForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model;
		validator.validate(editForm, result);

		if (!result.hasErrors()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Member member = (Member) authentication.getPrincipal();

			member.setEmail(editForm.getEmail());

			if (editForm.getPassword().length() > 0) {
				member.setPassword(DigestUtils.md5Hex(editForm.getPassword()));
			}
			member.setFirstName(editForm.getFirstName());
			member.setLastName(editForm.getLastName());
			member.setUsername(editForm.getUsername());
			
			double fee = Double.parseDouble(editForm.getFee());
			member.setFee(fee);
			
			memberDao.save(member);
			model = new ModelAndView("profile");
			model.addObject("member", member);
		} else {
			model = new ModelAndView("edit", "editForm", editForm);
		}
		return model;
	}
}
