/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team9.tutoragency.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 *Unit tests for the class {@link IndexController}.
 * @author curtys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
"file:src/main/webapp/WEB-INF/config/springMVC.xml",
"file:src/main/webapp/WEB-INF/config/springData.xml" })
@Transactional
@Rollback
public class IndexControllerTest {
    
    @Autowired private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Test of info method, of class InfoController.
     */
    @Test
    public void testInfo() throws Exception {
//        mockMvc.perform(get("/info"))
//                .andExpect(status().isOk());
    }

    /**
     * Test of faq method, of class InfoController.
     */
//    @Test
//    public void testFaq() {
//        System.out.println("faq");
//        InfoController instance = new InfoController();
//        ModelAndView expResult = null;
//        ModelAndView result = instance.faq();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of agb method, of class InfoController.
//     */
//    @Test
//    public void testAgb() {
//        System.out.println("agb");
//        InfoController instance = new InfoController();
//        ModelAndView expResult = null;
//        ModelAndView result = instance.agb();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of impressum method, of class InfoController.
//     */
//    @Test
//    public void testImpressum() {
//        System.out.println("impressum");
//        InfoController instance = new InfoController();
//        ModelAndView expResult = null;
//        ModelAndView result = instance.impressum();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
