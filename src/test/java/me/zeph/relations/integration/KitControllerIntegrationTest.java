package me.zeph.relations.integration;

import me.zeph.relations.Application;
import me.zeph.relations.model.api.Kit;
import org.codehaus.jackson.map.ObjectMapper;
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

import javax.transaction.Transactional;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
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
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Kit 99 not found")));
	}

	@Test
	public void shouldReturnTypeMissMatchMessage() throws Exception {
		mockMvc.perform(get("/kits/abc")
				.accept(APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("Failed to convert value of type 'java.lang.String' to required type 'long'; " +
						"nested exception is java.lang.NumberFormatException: For input string: \"abc\"")));
	}

	@Test
	public void shouldReturnKitCreate() throws Exception {
		Kit kit = new Kit();
		kit.setName("kitName");
		mockMvc.perform(post("/kits")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(kit)))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnKitConflict() throws Exception {
		Kit kit = new Kit();
		kit.setName("AGCU211");
		mockMvc.perform(post("/kits")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(kit)))
				.andExpect(status().isConflict());
	}
}