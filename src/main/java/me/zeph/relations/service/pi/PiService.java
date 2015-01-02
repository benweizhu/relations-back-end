package me.zeph.relations.service.pi;

import me.zeph.relations.dao.AlleleValueDao;
import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.ParentsReqParam;
import me.zeph.relations.service.pi.formula.OneParentFormula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiService {

	private AlleleValueDao alleleDao;
	private List<OneParentFormula> oneParentFormulas;

	@Autowired
	public PiService(AlleleValueDao alleleDao, List<OneParentFormula> oneParentFormulas) {
		this.alleleDao = alleleDao;
		this.oneParentFormulas = oneParentFormulas;
	}

	public double calculateParentsPi(ParentsReqParam reqParam) {
		return 0;
	}

	private double calculatePiWithFormula(OneParentReqParam param, OneParentFormula formula) {
		double c1Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getC1());
		double c2Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getC2());
		double af1Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf1());
		double af2Value = alleleDao.getValue(param.getKit(), param.getLocus(), param.getAf2());

		return formula.calculate(c1Value, c2Value, af1Value, af2Value, param);
	}

	public double calculateOneParentPi(OneParentReqParam reqParam) {
		double pi = 0;
		for (OneParentFormula formula : oneParentFormulas) {
			pi = calculatePiWithFormula(reqParam, formula);
			if (pi != 0) {
				return pi;
			}
		}
		return pi;
	}
}
