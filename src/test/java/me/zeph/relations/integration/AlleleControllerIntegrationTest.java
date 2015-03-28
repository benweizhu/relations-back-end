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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	public void shouldReturnAllelesSuccessfully() throws Exception {
		mockMvc.perform(get("/loci/2/alleles")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].alleleValue", is(14.0)))
				.andExpect(jsonPath("$[0].probability", is(0.0393)))
				.andExpect(jsonPath("$[1].alleleValue", is(15.0)))
				.andExpect(jsonPath("$[1].probability", is(0.3541)));
	}

	@Test
	public void shouldReturnLocusNotFoundException() throws Exception {
		mockMvc.perform(get("/loci/99/alleles")
				.accept(APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Locus 99 not found")));
	}

	@Test
	public void shouldReturnAlleleByLocusIdAndAlleleValueSuccessfully() throws Exception {
		mockMvc.perform(get("/loci/2/alleles/14")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.alleleValue", is(14.0)))
				.andExpect(jsonPath("$.probability", is(0.0393)));
	}

	@Test
	public void shouldReturnAlleleNotFoundException() throws Exception {
		mockMvc.perform(get("/loci/2/alleles/99")
				.accept(APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Allele 99.000000 not found in Locus 2")));
	}

	@Test
	public void shouldSaveAlleleSuccessfully() throws Exception {
		Allele allele = new Allele();
		allele.setAlleleValue(20);
		allele.setProbability(0.0017);
		mockMvc.perform(post("/loci/2/alleles")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(allele)))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnLocusNotFoundExceptionWhenSaveAllele() throws Exception {
		Allele allele = new Allele();
		allele.setAlleleValue(20);
		allele.setProbability(0.0017);
		mockMvc.perform(post("/loci/99/alleles")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(allele)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Locus 99 not found")));
	}

	@Test
	public void shouldReturnAlleleAlreadyExistExceptionWhenSaveAllele() throws Exception {
		Allele allele = new Allele();
		allele.setAlleleValue(14);
		allele.setProbability(0.0393);
		mockMvc.perform(post("/loci/2/alleles")
				.contentType(APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(allele)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message", is("Allele 14.000000 already exist in Locus 2")));
	}

}