package team9.tutoragency.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class UniversityAccessService {
	@Autowired
	UniversityDao uniDao;

	@Transactional
	public List<University> findAll() {
		return Lists.newArrayList(uniDao.findAll());
	}
	
	@Transactional
	public List<University> findByName(String name){
		return Lists.newArrayList(uniDao.findByName(name));
	}
}
