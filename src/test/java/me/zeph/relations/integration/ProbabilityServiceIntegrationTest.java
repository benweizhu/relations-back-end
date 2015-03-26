package me.zeph.relations.integration;

import me.zeph.relations.Application;
import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.service.ProbabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProbabilityServiceIntegrationTest {

	@Autowired
	private ProbabilityService probabilityService;

	@Test(expected = AlleleNotFoundException.class)
	public void shouldThrowAlleleNotFoundException() {
		probabilityService.getProbability(99, 1);
	}
}