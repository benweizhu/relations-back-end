package me.zeph.relations.integration;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.dao.AlleleValueDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class AlleleValueDaoIntegrationTest {

	@Autowired
	private AlleleValueDao alleleValueDao;

	@Test
	public void shouldTranslateStringToFloat() {
		assertEquals(0.3541, alleleValueDao.getValue("AGCU_EX22", "D3S1358", 15), 0.0001);
	}
}