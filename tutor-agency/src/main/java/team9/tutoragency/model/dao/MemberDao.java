package team9.tutoragency.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Member;

public interface MemberDao extends CrudRepository<Member,Long> {

	public List<Member> findByUsername(String username);
}
