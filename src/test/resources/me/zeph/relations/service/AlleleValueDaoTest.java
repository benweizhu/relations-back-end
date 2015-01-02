package me.zeph.relations.service;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.dao.AlleleValueDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class AlleleValueDaoTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void shouldTranslateStringToFloat() {
		assertEquals(0.3541, new AlleleValueDao(applicationContext).getValue("AGCU_EX22", "D3S1358", 15), 0.0001);
	}
}