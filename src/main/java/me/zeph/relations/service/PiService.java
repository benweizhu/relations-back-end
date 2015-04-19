package me.zeph.relations.service;

import me.zeph.relations.model.OneParentLocusRecord;
import me.zeph.relations.model.ParentsLocusRecord;
import me.zeph.relations.model.Unit;
import me.zeph.relations.model.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiService {

	@Autowired
	private AlleleService alleleService;

	@Autowired
	private CalculatorService calculatorService;

	public double calculateParentsPi(ParentsReqParam param) {
		Unit c1 = getUnit(param.getLocus(), param.getC1());
		Unit c2 = getUnit(param.getLocus(), param.getC2());
		Unit m1 = getUnit(param.getLocus(), param.getM1());
		Unit m2 = getUnit(param.getLocus(), param.getM2());
		Unit af1 = getUnit(param.getLocus(), param.getAf1());
		Unit af2 = getUnit(param.getLocus(), param.getAf2());
		ParentsLocusRecord record = new ParentsLocusRecord(c1, c2, m1, m2, af1, af2);
		return calculatorService.calculatePi(record.getPattern(), record.getP(), record.getQ());
	}

	public double calculateOneParentPi(OneParentReqParam param) {
		Unit c1 = getUnit(param.getLocus(), param.getC1());
		Unit c2 = getUnit(param.getLocus(), param.getC2());
		Unit af1 = getUnit(param.getLocus(), param.getAf1());
		Unit af2 = getUnit(param.getLocus(), param.getAf2());
		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);
		return calculatorService.calculatePi(record.getPattern(), record.getP(), record.getQ());
	}

	private Unit getUnit(long locus, double alleleValue) {
		return new Unit(alleleValue, alleleService.getAllele(locus, alleleValue).getProbability());
	}

	public CPIValue calculateCPI(double[] pis) {
		double cpi = 1;
		for (double pi : pis) {
			cpi = cpi * pi;
		}
		return new CPIValue(cpi);
	}

	public RCPValue calculateRCP(double cpi) {
		return new RCPValue(cpi / (1 + cpi));
	}

}
