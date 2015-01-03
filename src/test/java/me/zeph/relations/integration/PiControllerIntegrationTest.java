package me.zeph.relations.integration;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.model.OneParentReqParam;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
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
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class PiControllerIntegrationTest {

	private static final String ONE_PARENT_URL = "/pi/oneparent";
	private static final String KIT = "AGCU_EX22";
	private static final String LOCUS = "D3S1358";
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
		OneParentReqParam param = new OneParentReqParam();
		param.setKit(KIT);
		param.setLocus(LOCUS);
		param.setC1(14);
		param.setC2(15);
		param.setAf1(14);
		param.setAf2(15);
		mockMvc.perform(post(ONE_PARENT_URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(param)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.value", is(7.067338405145683)));
	}
}