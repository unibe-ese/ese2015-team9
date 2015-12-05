
package team9.tutoragency.controller.service;

import java.util.ArrayList;
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
public class UniversityServiceTest {
    
    @Mock
    private UniversityDao uniDao;
    @InjectMocks
    private BasicDataServiceImpl service;
    private final List<University> listUni1 = new ArrayList<>();
    private final List<University> listAllUnis = new ArrayList<>();
	private final List<University> listUni2 = new ArrayList<>();
    private final List<String> listUniNames = new ArrayList<>();
    
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
        
        listUniNames.add(uni1.getName());
        listUniNames.add(uni2.getName());
           
        Mockito.when(uniDao.findAll()).thenReturn(listAllUnis);
    }
    /**
     * Test of findAll method, of class {@link UniversityServiceImpl}.
     */
    @Test
    public void testFindAll() {
        List<University> result = service.findAllUniversites();
        assertEquals(listAllUnis, result);
    }

    /**
     * Test of findByNames method, of class {@link UniversityServiceImpl}.
     */
    @Test
    public void testFindByNames() {
        Mockito.when(uniDao.findByNameIn(listUniNames)).thenReturn(listAllUnis);
        
        List<String> names = null;
        List<University> expResult = new ArrayList<>();
        List<University> result = service.findUniversitiesByNames(names);
        assertEquals(expResult, result);
        names = new ArrayList<>();
        result = service.findUniversitiesByNames(names);
        assertEquals(expResult, result);
            
        result = service.findUniversitiesByNames(listUniNames);
        assertEquals(listAllUnis, result);
    }

    /**
     * Test of findAllNames method, of class {@link UniversityServiceImpl}.
     */
    @Test
    public void testFindAllNames() {
        List<String> result = service.findAllUniversityNames();
        assertEquals(listUniNames, result);
    }
    
}
