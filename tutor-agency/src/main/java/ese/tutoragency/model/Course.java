
package ese.tutoragency.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Course implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
}
