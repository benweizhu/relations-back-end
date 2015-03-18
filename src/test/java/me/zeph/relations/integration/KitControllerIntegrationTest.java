package me.zeph.relations.integration;

import me.zeph.relations.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class KitControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturnKits() throws Exception {
		mockMvc.perform(get("/kits")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].kitId", is(1)))
				.andExpect(jsonPath("$[0].name", is("AGCU211")));
	}

	@Test
	public void shouldReturnKitById() throws Exception {
		mockMvc.perform(get("/kits/1")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.kitId", is(1)))
				.andExpect(jsonPath("$.name", is("AGCU211")));
	}

	@Test
	public void shouldReturnKitNotFoundErrorMessage() throws Exception {
		mockMvc.perform(get("/kits/99")
				.accept(APPLICATION_JSON))
				.andExpect(status().isNotFound());
//				.andExpect(jsonPath("$.message", is("Kit 99 not found")));
	}
}