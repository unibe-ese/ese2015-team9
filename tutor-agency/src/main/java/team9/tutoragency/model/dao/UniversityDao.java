package team9.tutoragency.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.University;

public interface UniversityDao extends CrudRepository<University,Long> {
	
	public List<University> findByName(String name);

	public List<University> findByNameIn(List<String> universityNames);
}