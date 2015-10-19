
package ese.tutoragency.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "MEMBERS")
public class Member implements Serializable {
    
    @Id
    @Column(name = "MEMBER_ID", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "IS_TUTOR")
    private boolean isTutor;
    @Column(name = "FEE")
    private Double fee;             // Use of Wrapper class because this variable is not always set.
    @Column(name = "IS_ACTIVATED")
    private boolean isActivated;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MEMBER_COURSE",
            joinColumns
            = @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    )
    private List<Course> courseList;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MEMBER_UNIVERSITY",
            joinColumns
            = @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "UNIVERSITY_ID", referencedColumnName = "UNIVERSITY_ID")
    )
    private List<University> universityList;
    
    protected Member() {}
    
    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isTutor = false;
        this.isActivated = false;
        this.fee = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsTutor() {
        return isTutor;
    }

    public void setIsTutor(boolean isTutor) {
        this.isTutor = isTutor;
    }

    public boolean isIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
    
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<University> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<University> universityList) {
        this.universityList = universityList;
    }
    
    
    
}
