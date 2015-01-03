package me.zeph.relations.service.pi;

import me.zeph.relations.dao.AlleleValueDao;
import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.ParentsReqParam;
import me.zeph.relations.pattern.Calculator;
import me.zeph.relations.pattern.OneParentLocusRecord;
import me.zeph.relations.pattern.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiService {

	private AlleleValueDao alleleDao;
	private Calculator calculator;

	@Autowired
	public PiService(AlleleValueDao alleleDao, Calculator calculator) {
		this.alleleDao = alleleDao;
		this.calculator = calculator;
	}

	public double calculateParentsPi(ParentsReqParam param) {
		return 0;
	}

	public double calculateOneParentPi(OneParentReqParam param) {

		double c1Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getC1());
		double c2Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getC2());
		double af1Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf1());
		double af2Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf2());

		Unit c1 = new Unit(param.getC1(), c1Value);
		Unit c2 = new Unit(param.getC2(), c2Value);
		Unit af1 = new Unit(param.getAf1(), af1Value);
		Unit af2 = new Unit(param.getAf2(), af2Value);

		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);

		return calculator.calculatePi(record.getPattern(), record.getP(), record.getQ());
	}

}
