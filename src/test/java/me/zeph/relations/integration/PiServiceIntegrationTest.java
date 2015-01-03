package me.zeph.relations.integration;

import me.zeph.relations.config.WebContextConfiguration;
import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.PiService;
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
public class PiServiceIntegrationTest {

	private static final double DELTA_8 = 0.00000001;

	@Autowired
	private PiService piService;

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {

		OneParentReqParam reqParam = prepareOneParentReqParam("AGCU_EX22", "D3S1358", 15, 15, 15, 15);

		double pi = piService.calculateOneParentPi(reqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}

	private OneParentReqParam prepareOneParentReqParam(String kit, String locus, int af1, int af2, int c1, int c2) {
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setKit(kit);
		reqParam.setLocus(locus);
		reqParam.setC1(c1);
		reqParam.setC2(c2);
		reqParam.setAf1(af1);
		reqParam.setAf2(af2);
		return reqParam;
	}
}