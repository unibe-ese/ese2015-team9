package team9.tutoragency.model.dao;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Member;

public interface MemberDao extends CrudRepository<Member,Long> {
}
