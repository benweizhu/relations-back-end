package me.zeph.relations.service;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.model.OneParentReqParam;
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
public class PiServiceTest {

	private static final double DELTA_8 = 0.00000001;

	@Autowired
	private PiService piService;

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setKit("AGCU_EX22");
		reqParam.setLocus("D3S1358");
		reqParam.setAf1(15);
		reqParam.setAf2(15);
		reqParam.setC1(15);
		reqParam.setC2(15);

		double pi = piService.calculateOneParentPi(reqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}
}