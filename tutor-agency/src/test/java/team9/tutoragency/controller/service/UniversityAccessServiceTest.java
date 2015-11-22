
package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.UniversityDao;

@RunWith(MockitoJUnitRunner.class)
public class UniversityAccessServiceTest {
    
    @Mock
    private UniversityDao uniDao;
    @InjectMocks
    private UniversityService service;
    private final List<University> listUni1 = new ArrayList<University>();
    private final List<University> listAllUnis = new ArrayList<University>();
	private final List<University> listUni2 = new ArrayList<University>();
    
    @Before
    public void setUp() {
        University uni1 = new University();
        University uni2 = new University();
        uni1.setName("uni1");      
        uni1.setName("uni2");     
        listUni1.add(uni1);  
        listUni2.add(uni2);
        
        listAllUnis.add(uni1);
        listAllUnis.add(uni2);
        
        Mockito.when(uniDao.findByName("uni1")).thenReturn(listUni1);
        Mockito.when(uniDao.findByName("uni2")).thenReturn(listUni2);
        Mockito.when(uniDao.findAll()).thenReturn(listAllUnis);
    }
    /**
     * Test of findAll method, of class {@link UniversityService}.
     */
    @Test
    public void testFindAll() {
        List<University> result = service.findAll();
        assertEquals(listAllUnis, result);
    }
    /**
     * Test of findByName method, of class {@link UniversityService}.
     */
    @Test
    public void testFindByName() {
        String name = "uni1";
        List<University> result = service.findByName(name);
        assertEquals(listUni1, result);
    }
    @Test
    public void testFindByNameList(){
    	assertEquals(Arrays.asList(new University[]{}), service.findByNames(null));
    	
    	Mockito.when(uniDao.findByName("uni1")).thenReturn(listUni1);
    	Mockito.when(uniDao.findByName("uni2")).thenReturn(listUni2);
    	List<String> names = Arrays.asList(new String[]{"uni1", "uni2"});
    	List<University> result = service.findByNames(names);
    	assertEquals(listAllUnis, result);
    	
    }
    
}
