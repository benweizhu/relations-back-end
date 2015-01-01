package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneInQFormulaTest {

	public static final double DELTA_8 = 0.0000001;

	@Test
	public void shouldCalculateOneInQ() {
		OneInQFormula oneInQFormula = new OneInQFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setAf1(1);
		reqParam.setAf2(1);
		reqParam.setC1(1);
		reqParam.setC2(1);

		float pi = oneInQFormula.calculate(0.0393f, 0.0393f, 0.0393f, 0.0393f, reqParam);

		assertEquals(25.4452934f, pi, DELTA_8);
	}
}