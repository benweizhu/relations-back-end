package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.repository.AlleleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.ALLELE_NOT_FOUND_EXCEPTION;

@Service
public class ProbabilityService {

	@Autowired
	private AlleleRepository alleleRepository;

	public double getProbability(long locusId, double allele) {
		AlleleEntity alleleEntity = alleleRepository.findByLocusIdAndAllele(locusId, allele);
		if (alleleEntity == null) {
			throw new AlleleNotFoundException(format(ALLELE_NOT_FOUND_EXCEPTION, allele, locusId));
		}
		return alleleEntity.getProbability();
	}

}
