
package team9.tutoragency.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Course implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    @ManyToOne()
    private University university;
    
    @ManyToMany(mappedBy="courseList")
    private List<Member> members;

    public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
    public boolean equals(Object o){
    	if( o.getClass().equals(this.getClass())){
    		Course course = (Course) o;
    		if(this.id == course.id)
    			return true;
    		else
    			return false;
    	} else 
    		return false;
    		
    	
    }
}
