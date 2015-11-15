package team9.tutoragency.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.controller.service.UniversityAccessService;
import team9.tutoragency.model.University;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

	@Mock
	SearchService searchService;
	@Mock
	UniversityAccessService uniService;
	@InjectMocks
	SearchController controller = new SearchController();

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		/*To avoid some "circular-view path" exception... 
		standard view-resolver sees a circle, because search is forwarded to search.
		So we resolve the forwarded view as search.jsp to avoid the circle.*/
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();		
		viewResolver.setSuffix(".jsp");
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysExpect(status().isOk())
				.setViewResolvers(viewResolver).build();

	}

	@Test
	public void testSearch() {
		List<String> uniNameList = Arrays.asList(new String[] { "name", "name2" });
		when(uniService.findAllNames()).thenReturn(uniNameList);
		try {
			mockMvc.perform(get("/search")).andExpect(forwardedUrl("search.jsp")).andExpect(model().attribute("universities", uniNameList))
					.andExpect(model().attribute("form", new SearchForm()));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
}
