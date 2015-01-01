package me.zeph.relations.service.pi;

import me.zeph.relations.dao.AlleleValueDao;
import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.ParentsReqParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiService {

	@Autowired
	private AlleleValueDao alleleValueDao;

	public PiService() {
	}

	public PiService(AlleleValueDao alleleValueDao) {
		this.alleleValueDao = alleleValueDao;
	}

	public float calculateParentsPi(ParentsReqParam reqParam) {
		return 0;
	}

	public float calculateOneParentPi(OneParentReqParam reqParam) {
		float af1 = reqParam.getAf1();
		float af2 = reqParam.getAf2();
		float c1 = reqParam.getC1();
		float c2 = reqParam.getC2();
		float pi = 0;

		float c1value = alleleValueDao.getValue(reqParam.getKit(), reqParam.getLocus(), reqParam.getC1());
		float c2value = alleleValueDao.getValue(reqParam.getKit(), reqParam.getLocus(), reqParam.getC2());
		float af1value = alleleValueDao.getValue(reqParam.getKit(), reqParam.getLocus(), reqParam.getAf1());
		float af2value = alleleValueDao.getValue(reqParam.getKit(), reqParam.getLocus(), reqParam.getAf2());

		if (af1 == af2 && c1 == c2 && af1 == c1) {
			pi = 1 / c1value;
		} else if (c1 != c2 && af1 == af2 && (af1 == c1 || af1 == c2)) {
			pi = 1 / af1value * 2;
		} else if (af1 != af2 && c1 == c2 && (c1 == af1 || c1 == af2)) {
			pi = 1 / c1value * 2;
		} else if (c1 != c2 && af1 != af2 && (c1 == af1 || c1 == af2) && (c2 == af1 || c2 == af2)) {
			pi = c1value + c2value / (4 * c1value) * c2value;
		} else if (c1 != c2 && af1 != af2 && ((c1 == af1 && c1 != af2) || (c2 == af1 && c2 != af2))) {
			if (c1 == af1 && c1 != af2) {
				pi = 1 / (4 * c1value);
			} else if (c2 == af1 && c2 != af2) {
				pi = 1 / (4 * c2value);
			}
		}
		return pi;
	}
}
