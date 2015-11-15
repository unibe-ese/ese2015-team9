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
public class UniversityAccessService {
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
	 * Returns a list of universities, as if in the list with names, the
	 * duplicates were removed. And then the results, obtained from calling for each name
	 * {@link #findByName(String)}, would be joined together.
	 * 
	 * @param names
	 *            if null, an empty List is returned.
	 * 
	 */
	@Transactional
	public List<University> findByNames(List<String> names) {
		if (names == null)
			return new ArrayList<University>();
		List<University> unis = new ArrayList<University>();
		List<String> copy = new ArrayList<String>(names);
		for (String name : names) {
			copy.remove(name);
			if (!copy.contains(name))
				unis.addAll(uniDao.findByName(name));
		}
		return unis;
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
