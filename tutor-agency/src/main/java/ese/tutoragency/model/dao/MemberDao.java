package ese.tutoragency.model.dao;

import org.springframework.data.repository.CrudRepository;

import ese.tutoragency.model.Member;

public interface MemberDao extends CrudRepository<Member,Long> {
}
