package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public class EntityService<E, T extends CrudRepository<E, Long>> {

	protected T dao;
	
	protected EntityService(T dao){
		this.dao = dao;
	}
	
	public List<E> findAll(){
		Iterable<E> entities = dao.findAll();
		List<E> list = new ArrayList<E>();
		
		for (E e : entities) {
			list.add(e);
		}	
		return list;
	}
	/**
	 * @param id can be null
	 * @return an empty Optional if id is null or no entity under this id found.
	 */
	public Optional<E> findOne(Long id){
		if(id==null) 
			return Optional.empty();
		else 
			return Optional.ofNullable(dao.findOne(id));
	}
	
	
}
