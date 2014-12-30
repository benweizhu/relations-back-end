package me.zeph.relations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static java.lang.Float.valueOf;
import static java.util.Locale.getDefault;

@Service
public class AlleleValueService {

	@Autowired
	private ApplicationContext applicationContext;

	public AlleleValueService() {
	}

	public AlleleValueService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public float getValue(String kit, String locus, float allele) {
		return valueOf(applicationContext.getMessage(getAllele(kit, locus, allele), null, getDefault()));
	}

	private String getAllele(String kit, String locus, float allele) {
		return kit + "." + locus + "." + allele;
	}
}
