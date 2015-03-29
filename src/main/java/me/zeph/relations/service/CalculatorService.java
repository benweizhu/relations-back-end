package me.zeph.relations.service;

import me.zeph.relations.dao.FormulaDao;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

	private static final String P = "p";
	private static final String Q = "q";

	@Autowired
	private FormulaDao formulaDao;
	@Autowired
	private JexlEngine calculateEngine;

	public double calculatePi(String pattern, double p, double q) {
		String formula = formulaDao.getFormulaByPattern(pattern);
		Expression expression = calculateEngine.createExpression(formula);
		JexlContext context = setUpContext(p, q);
		return ((Double) expression.evaluate(context)).doubleValue();
	}

	private JexlContext setUpContext(double p, double q) {
		JexlContext context = new MapContext();
		context.set(P, p);
		context.set(Q, q);
		return context;
	}
}
