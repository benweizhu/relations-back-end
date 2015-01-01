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
		return 0;
	}
}
