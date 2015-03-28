package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.repository.AlleleRepository;
import me.zeph.relations.repository.LocusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.ALLELE_NOT_FOUND;
import static me.zeph.relations.exception.ExceptionMessage.LOCUS_NOT_FOUND;

@Service
public class AlleleService {

	@Autowired
	private AlleleRepository alleleRepository;
	@Autowired
	private LocusRepository locusRepository;

	public List<Allele> getAlleles(long locusId) {
		assertLocusExist(locusId);
		List<AlleleEntity> alleleEntities = alleleRepository.findByLocusId(locusId);
		return translateAlleles(alleleEntities);
	}

	public Allele getAllele(long locusId, double alleleValue) {
		AlleleEntity alleleEntity = alleleRepository.findByLocusIdAndAllele(locusId, alleleValue);
		assertAlleleExist(locusId, alleleValue, alleleEntity);
		return translateAllele(alleleEntity);
	}

	public void addAllele(Allele requestAllele) {

	}

	private List<Allele> translateAlleles(List<AlleleEntity> alleleEntities) {
		List<Allele> alleles = newArrayList();
		for (AlleleEntity alleleEntity : alleleEntities) {
			alleles.add(translateAllele(alleleEntity));
		}
		return alleles;
	}

	private Allele translateAllele(AlleleEntity alleleEntity) {
		Allele allele = new Allele();
		allele.setAlleleValue(alleleEntity.getAllele());
		allele.setProbability(alleleEntity.getProbability());
		return allele;
	}

	private void assertLocusExist(long locusId) {
		if (!locusRepository.exists(locusId)) {
			throw new LocusNotFoundException(format(LOCUS_NOT_FOUND, locusId));
		}
	}

	private void assertAlleleExist(long locusId, double alleleValue, AlleleEntity alleleEntity) {
		if (alleleEntity == null) {
			throw new AlleleNotFoundException(format(ALLELE_NOT_FOUND, alleleValue, locusId));
		}
	}
}
