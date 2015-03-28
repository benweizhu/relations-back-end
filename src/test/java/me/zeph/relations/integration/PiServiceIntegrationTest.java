package me.zeph.relations.integration;

import me.zeph.relations.Application;
import me.zeph.relations.model.api.OneParentReqParam;
import me.zeph.relations.model.api.ParentsReqParam;
import me.zeph.relations.service.PiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PiServiceIntegrationTest {

	private static final double DELTA_8 = 0.00000001;
	private static final long LOCUS = 4L;
	@Autowired
	private PiService piService;

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {
		OneParentReqParam reqParam = prepareOneParentReqParam(LOCUS, 15, 15, 15, 15);

		double pi = piService.calculateOneParentPi(reqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}

	private OneParentReqParam prepareOneParentReqParam(long locus, int af1, int af2, int c1, int c2) {
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setLocus(locus);
		reqParam.setC1(c1);
		reqParam.setC2(c2);
		reqParam.setAf1(af1);
		reqParam.setAf2(af2);
		return reqParam;
	}

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_rq_qq() {
		ParentsReqParam parentsReqParam = prepareParentsReqParam(LOCUS, 15, 15, 16, 15, 15, 15);

		double pi = piService.calculateParentsPi(parentsReqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}

	private ParentsReqParam prepareParentsReqParam(long locus, int c1, int c2,
	                                               int m1, int m2, int af1, int af2) {
		ParentsReqParam parentsReqParam = new ParentsReqParam();
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