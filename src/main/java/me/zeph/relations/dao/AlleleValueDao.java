package me.zeph.relations.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static java.lang.Double.valueOf;
import static java.util.Locale.getDefault;

@Component
public class AlleleValueDao {

	private static final String DOT_ZERO = ".0";
	private static final String DOT = ".";
	private static final String A = "a";

	private ApplicationContext applicationContext;

	@Autowired
	public AlleleValueDao(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public double getValue(String kit, String locus, double allele) {
		return valueOf(applicationContext.getMessage(getAllele(kit, locus, allele), null, getDefault()));
	}

	private String getAllele(String kit, String locus, double allele) {
		String kitLocusAllele = kit + DOT + locus + DOT + A + allele;
		if (kitLocusAllele.endsWith(DOT_ZERO)) {
			kitLocusAllele = StringUtils.delete(kitLocusAllele, DOT_ZERO);
		}
		return kitLocusAllele;
	}
}
