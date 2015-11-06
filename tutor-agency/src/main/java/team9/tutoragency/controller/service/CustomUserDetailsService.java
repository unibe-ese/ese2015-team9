package team9.tutoragency.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.exceptions.UsernameDuplicateException;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberDao memberDao;
	
	public UserDetails loadUserByUsername(String username)
			   throws UsernameNotFoundException, DataAccessException, UsernameDuplicateException {
			   
			  Member member;
			   
			  List<Member> matchingMembers = memberDao.findByUsername(username);
			  
			  if(matchingMembers.size() >= 2)
				  throw new UsernameDuplicateException("Duplicated Username!");
			  else 
				  if(matchingMembers.size() == 0) 
					   throw new UsernameNotFoundException("No Member with this name found!"); 
				  else
					   member = matchingMembers.get(0);
		   
			  return member;
	}
}
