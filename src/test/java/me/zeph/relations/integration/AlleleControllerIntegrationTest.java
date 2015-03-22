package me.zeph.relations.integration;

import me.zeph.relations.Application;
import me.zeph.relations.model.api.Allele;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class AlleleControllerIntegrationTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturnAllelesByKitId() throws Exception {
		mockMvc.perform(get("/kits/1/alleles")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].alleleId", is(1)))
				.andExpect(jsonPath("$[0].name", is("D1GATA113")));
	}


	@Test
	public void shouldReturnAlleleByKitIdAndAlleleId() throws Exception {
		mockMvc.perform(get("/kits/1/alleles/1")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.alleleId", is(1)))
				.andExpect(jsonPath("$.name", is("D1GATA113")));
	}

	@Test
	public void shouldReturnAlleleNotFoundWhenGetAlleleById() throws Exception {
		mockMvc.perform(get("/kits/1/alleles/99")
				.accept(APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Allele 99 not found in Kit 1")));
	}

	@Test
	public void shouldReturnKitNotFoundWhenGetAlleleById() throws Exception {
		mockMvc.perform(get("/kits/99/alleles/1")
				.accept(APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Kit 99 not found")));
	}

	@Test
	public void shouldReturnAlleleCreated() throws Exception {
		Allele value = new Allele();
		value.setName("alleleName");
		mockMvc.perform(post("/kits/1/alleles/")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(value)))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnAlleleConflict() throws Exception {
		Allele value = new Allele();
		value.setName("D1GATA113");
		mockMvc.perform(post("/kits/1/alleles/")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(value)))
				.andExpect(status().isConflict());
	}

	@Test
	public void shouldReturnKitNotFoundWhenAddAllele() throws Exception {
		Allele value = new Allele();
		value.setName("D1GATA113");
		mockMvc.perform(post("/kits/99/alleles/")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(value)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Kit 99 not found")));
	}

	@Test
	public void shouldReturnNotContentWhenDeleteAlleleSuccessfully() throws Exception {
		mockMvc.perform(delete("/kits/1/alleles/1"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturnKitNotFoundWhenDeleteAllele() throws Exception {
		mockMvc.perform(delete("/kits/99/alleles/1"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Kit 99 not found")));
	}

	@Test
	public void shouldReturnAlleleNotFoundWhenDeleteAllele() throws Exception {
		mockMvc.perform(delete("/kits/2/alleles/3"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Allele 3 not found in Kit 2")));
	}
}