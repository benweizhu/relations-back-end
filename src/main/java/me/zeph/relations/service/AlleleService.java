package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleAlreadyExistException;
import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.LocusEntity;
import me.zeph.relations.repository.AlleleRepository;
import me.zeph.relations.repository.LocusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.*;

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

	public LocusEntity addAllele(long locusId, Allele requestAllele) {
		LocusEntity locusEntity = locusRepository.findOne(locusId);
		if (locusEntity == null) {
			throw new LocusNotFoundException(format(LOCUS_NOT_FOUND, locusId));
		} else {
			AlleleEntity alleleEntity = translateToAlleleEntity(requestAllele, locusEntity);
			assertAlleleNotExist(locusId, requestAllele, locusEntity, alleleEntity);
			locusEntity.addAllele(alleleEntity);
			return locusRepository.saveAndFlush(locusEntity);
		}
	}

	public void removeAllele(long locusId, double alleleValue) {
		LocusEntity locusEntity = locusRepository.findOne(locusId);
		assertLocusExist(locusId, locusEntity);
		AlleleEntity alleleEntity = alleleRepository.findByLocusIdAndAllele(locusId, alleleValue);
		assertAlleleExist(locusId, alleleValue, alleleEntity);
		alleleRepository.delete(alleleEntity);
		alleleRepository.flush();
	}

	private AlleleEntity translateToAlleleEntity(Allele allele, LocusEntity locusEntity) {
		AlleleEntity alleleEntity = new AlleleEntity();
		alleleEntity.setAllele(allele.getAlleleValue());
		alleleEntity.setProbability(allele.getProbability());
		alleleEntity.setLocus(locusEntity);
		return alleleEntity;
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

	private void assertLocusExist(long locusId, LocusEntity locusEntity) {
		if (locusEntity == null) {
			throw new LocusNotFoundException(format(LOCUS_NOT_FOUND, locusId));
		}
	}

	private void assertLocusExist(long locusId) {
		if (!locusRepository.exists(locusId)) {
			throw new LocusNotFoundException(format(LOCUS_NOT_FOUND, locusId));
		}
	}

	private void assertAlleleNotExist(long locusId, Allele requestAllele, LocusEntity locusEntity, AlleleEntity alleleEntity) {
		if (locusEntity.getAlleles().contains(alleleEntity)) {
			throw new AlleleAlreadyExistException(format(ALLELE_ALREADY_EXIST, requestAllele.getAlleleValue(), locusId));
		}
	}

	private void assertAlleleExist(long locusId, double alleleValue, AlleleEntity alleleEntity) {
		if (alleleEntity == null) {
			throw new AlleleNotFoundException(format(ALLELE_NOT_FOUND, alleleValue, locusId));
		}
	}
}
