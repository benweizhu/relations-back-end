package me.zeph.relations.service;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.ParentsReqParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiService {

	@Autowired
	private AlleleValueService alleleValueService;

	public PiService() {
	}

	public PiService(AlleleValueService alleleValueService) {
		this.alleleValueService = alleleValueService;
	}

	public float calculateParentsPi(ParentsReqParam reqParam) {
		return 0;
	}

	public float calculateOneParentPi(OneParentReqParam reqParam) {
		return 0;
	}
}
