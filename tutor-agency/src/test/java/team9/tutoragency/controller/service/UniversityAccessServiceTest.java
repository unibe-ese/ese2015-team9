
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
public class UniversityAccessServiceTest {
    
    @Mock
    private UniversityDao uniDao;
    @InjectMocks
    private UniversityAccessService service;
    private final List<University> expReslistSingle = new ArrayList<University>();
    private final List<University> expReslistAll = new ArrayList<University>();
    
    @Before
    public void setUp() {
        University uni1 = new University();
        University uni2 = new University();
        uni1.setName("uni1");      
        uni1.setName("uni2");     
        expReslistSingle.add(uni1);      
        expReslistAll.add(uni1);
        expReslistAll.add(uni2);
        
        Mockito.when(uniDao.findByName("uni1")).thenReturn(expReslistSingle);
        Mockito.when(uniDao.findAll()).thenReturn(expReslistAll);
    }

    @Test
    public void testFindAll() {
        List<University> result = service.findAll();
        assertEquals(expReslistAll, result);
    }

    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "uni1";
        List<University> result = service.findByName(name);
        assertEquals(expReslistSingle, result);
    }
    
}
