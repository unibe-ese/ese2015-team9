package team9.tutoragency.model.dao;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.University;

public interface UniversityDao extends CrudRepository<University,Long> {
}