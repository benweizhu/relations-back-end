package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.formula.oneparent.OneInQFormula;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OneInQFormulaTest extends FormulaTest {

	@Test
	public void shouldCalculateOneInQWhenInputIs_qq_qq() {
		OneInQFormula oneInQFormula = new OneInQFormula();

		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setAf1(15);
		reqParam.setAf2(15);
		reqParam.setC1(15);
		reqParam.setC2(15);

		double pi = oneInQFormula.calculate(0.3541d, 0.3541d, 0.3541d, 0.3541d, reqParam);

		assertEquals(2.82406099971759d, pi, DELTA_8);
	}
}