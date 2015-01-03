package me.zeph.relations.service;

import me.zeph.relations.dao.AlleleValueDao;
import me.zeph.relations.model.OneParentLocusRecord;
import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.ParentsReqParam;
import me.zeph.relations.model.Unit;
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

		Unit c1 = new Unit(param.getC1(), alleleDao.getValue(param.getKit(), param.getLocus(), param.getC1()));
		Unit c2 = new Unit(param.getC2(), alleleDao.getValue(param.getKit(), param.getLocus(), param.getC2()));
		Unit af1 = new Unit(param.getAf1(), alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf1()));
		Unit af2 = new Unit(param.getAf2(), alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf2()));

		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);

		return calculator.calculatePi(record.getPattern(), record.getP(), record.getQ());
	}

}
