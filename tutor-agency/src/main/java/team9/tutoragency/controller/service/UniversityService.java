package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class UniversityService {
	@Autowired
	UniversityDao uniDao;

	@Transactional
	public List<University> findAll() {
		return Lists.newArrayList(uniDao.findAll());
	}

	@Transactional
	public List<University> findByName(String name) {
		return Lists.newArrayList(uniDao.findByName(name));
	}

	/**
	 * Returns the result of {@code uniDao}'s {@code findByNameIn} method. Prevents the query from being
	 * invoked with an empty list. 
	 * @param names If null, an empty List is returned.
	 */
	@Transactional(readOnly=true)
	public List<University> findByNames(List<String> names) {
		if(names == null || names.isEmpty())
			return new ArrayList<University>();
		
		return uniDao.findByNameIn(names);
	}

	@Transactional(readOnly = true)
	public List<String> findAllNames() {
		List<String> names = new ArrayList<String>();
		for(University uni : uniDao.findAll()){
			names.add(uni.getName());
		}
		return names;
	}		
}
