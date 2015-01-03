package me.zeph.relations.service.pi.formula.oneparent;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.FormulaTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PPlusQInFourPQOneParentFormulaTest extends FormulaTest {
	@Test
	public void shouldCalculatePPlusQInForPQWhenInputIs_pq_pq() {
		PPlusQInFourPQOneParentFormula pPlusQInFourPQOneParentFormula = new PPlusQInFourPQOneParentFormula();
		OneParentReqParam reqParam = new OneParentReqParam();
		reqParam.setC1(14);
		reqParam.setC2(15);
		reqParam.setAf1(14);
		reqParam.setAf2(15);

		double pi = pPlusQInFourPQOneParentFormula.calculate(0.0393d, 0.3541d, 0.0393d, 0.3541d, reqParam);

		assertEquals(7.06733840514568d, pi, DELTA_8);
	}
}