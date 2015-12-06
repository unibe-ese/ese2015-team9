package team9.tutoragency.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.impl.AccountServiceImpl;
import team9.tutoragency.model.Member;

/**
 * Intercepts all urls and adds the {@link Member} to the model if the user is logged in.
 * This needs to be done in order for the header to display any request feedback messages to alert the user that
 * he has an open request he can accept or not.
 * @author laeri
 *
 */
public class HeaderInfoInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	MemberService memberService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (null == modelAndView) {
			return;
		}
		
		if(memberService.getAuthenticatedMember().isPresent()){
			modelAndView.addObject("loggedInMember", memberService.getAuthenticatedMember().get());
		}
	}

}
