package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.exception.LocusAlreadyExistException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.Locus;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.AlleleRepository;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.*;

@Transactional
@Service
public class LocusService {

	@Autowired
	private KitRepository kitRepository;

	@Autowired
	private AlleleRepository alleleRepository;

	public List<Locus> getLoci(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return translateLoci(kitEntity.getAlleles());
	}

	public Locus getLocus(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return findLocusById(kitId, alleleId, kitEntity.getAlleles());
	}

	public void addLocus(long kitId, String alleleName) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		addLocusToKit(alleleName, kitEntity);

		kitRepository.saveAndFlush(kitEntity);
	}

	public void removeLocus(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		removeLocusFromKit(kitId, alleleId, kitEntity);

		kitRepository.saveAndFlush(kitEntity);
	}

	private void removeLocusFromKit(long kitId, long alleleId, KitEntity kitEntity) {
		AlleleEntity allele = alleleRepository.findOne(alleleId);
		assertLocusExistInKit(kitId, alleleId, kitEntity, allele);
		kitEntity.removeAllele(allele);
	}

	private void addLocusToKit(String alleleName, KitEntity kitEntity) {
		AlleleEntity alleleEntity = getAlleleEntity(alleleName);
		assertNotLocusExistInKit(alleleName, kitEntity, alleleEntity);
		kitEntity.addAllele(alleleEntity);
	}

	private void assertLocusExistInKit(long kitId, long alleleId, KitEntity kitEntity, AlleleEntity allele) {
		if (!kitEntity.getAlleles().contains(allele)) {
			throw new LocusNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
		}
	}

	private void assertNotLocusExistInKit(String alleleName, KitEntity kitEntity, AlleleEntity alleleEntity) {
		if (kitEntity.getAlleles().contains(alleleEntity)) {
			throw new LocusAlreadyExistException(format(ALLELE_ALREADY_EXIST, alleleName));
		}
	}

	private void assertKitExist(long kitId, KitEntity kitEntity) {
		if (kitEntity == null) {
			throw new KitNotFoundException(format(KIT_NOT_FOUND, kitId));
		}
	}

	private AlleleEntity getAlleleEntity(String alleleName) {
		AlleleEntity alleleEntity = alleleRepository.findByName(alleleName);
		if (alleleEntity == null) {
			alleleEntity = new AlleleEntity(alleleName);
		}
		return alleleEntity;
	}

	private Locus findLocusById(long kitId, long alleleId, List<AlleleEntity> alleleEntities) {
		for (AlleleEntity alleleEntity : alleleEntities) {
			if (alleleEntity.getId() == alleleId) {
				return translateLocus(alleleEntity);
			}
		}
		throw new LocusNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
	}

	private List<Locus> translateLoci(List<AlleleEntity> alleleEntities) {
		ArrayList<Locus> loci = newArrayList();
		for (AlleleEntity alleleEntity : alleleEntities) {
			loci.add(translateLocus(alleleEntity));
		}
		return loci;
	}

	private Locus translateLocus(AlleleEntity alleleEntity) {
		Locus locus = new Locus();
		locus.setAlleleId(alleleEntity.getId());
		locus.setName(alleleEntity.getName());
		return locus;
	}
}
