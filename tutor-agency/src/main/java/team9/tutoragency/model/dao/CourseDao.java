package team9.tutoragency.model.dao;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Course;

public interface CourseDao extends CrudRepository<Course,Long> {
}