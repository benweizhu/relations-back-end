package me.zeph.relations.integration;

import me.zeph.relations.Application;
import me.zeph.relations.model.api.OneParentReqParam;
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

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PiControllerIntegrationTest {

	private static final String ONE_PARENT_URL = "/pi/oneparent";
	private static final long LOCUS = 4L;
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void shouldReturnPi() throws Exception {
		OneParentReqParam oneParentReqParam = new OneParentReqParam();
		oneParentReqParam.setLocus(LOCUS);
		oneParentReqParam.setC1(14);
		oneParentReqParam.setC2(15);
		oneParentReqParam.setAf1(14);
		oneParentReqParam.setAf2(15);
		mockMvc.perform(post(ONE_PARENT_URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(oneParentReqParam)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(7.067338405145683)));
	}

	@Test
	public void shouldReturnCpi() throws Exception {
		mockMvc.perform(post("/pi/cpi")
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(new double[]{0.5d, 0.5d, 0.5d})))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(0.125)));
	}

	@Test
	public void shouldReturnRcp() throws Exception {
		mockMvc.perform(post("/pi/rcp")
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(0.5)));
	}
}