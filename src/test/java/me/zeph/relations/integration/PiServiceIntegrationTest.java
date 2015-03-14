package me.zeph.relations.integration;

import me.zeph.relations.configuration.WebContextConfiguration;
import me.zeph.relations.model.api.OneParentReqParam;
import me.zeph.relations.model.api.ParentsReqParam;
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
	private static final String KIT = "AGCU_EX22";
	private static final String LOCUS = "D3S1358";

	@Autowired
	private PiService piService;

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {

		OneParentReqParam reqParam = prepareOneParentReqParam(KIT, LOCUS, 15, 15, 15, 15);

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

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_rq_qq() {

		ParentsReqParam parentsReqParam = prepareParentsReqParam(KIT, LOCUS, 15, 15, 16, 15, 15, 15);

		double pi = piService.calculateParentsPi(parentsReqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}

	private ParentsReqParam prepareParentsReqParam(String kit, String locus, int c1, int c2,
	                                               int m1, int m2, int af1, int af2) {
		ParentsReqParam parentsReqParam = new ParentsReqParam();
		parentsReqParam.setKit(kit);
		parentsReqParam.setLocus(locus);
		parentsReqParam.setC1(c1);
		parentsReqParam.setC2(c2);
		parentsReqParam.setM1(m1);
		parentsReqParam.setM2(m2);
		parentsReqParam.setAf1(af1);
		parentsReqParam.setAf2(af2);
		return parentsReqParam;
	}
}