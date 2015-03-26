package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.exception.LocusAlreadyExistException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.Locus;
import me.zeph.relations.model.entity.LocusEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.LocusRepository;
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
	private LocusRepository locusRepository;

	public List<Locus> getLoci(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return translateLoci(kitEntity.getLoci());
	}

	public Locus getLocus(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return findLocusById(kitId, alleleId, kitEntity.getLoci());
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
		LocusEntity allele = locusRepository.findOne(alleleId);
		assertLocusExistInKit(kitId, alleleId, kitEntity, allele);
		kitEntity.removeLocus(allele);
	}

	private void addLocusToKit(String alleleName, KitEntity kitEntity) {
		LocusEntity locusEntity = getAlleleEntity(alleleName);
		assertNotLocusExistInKit(alleleName, kitEntity, locusEntity);
		kitEntity.addLocus(locusEntity);
	}

	private void assertLocusExistInKit(long kitId, long alleleId, KitEntity kitEntity, LocusEntity allele) {
		if (!kitEntity.getLoci().contains(allele)) {
			throw new LocusNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
		}
	}

	private void assertNotLocusExistInKit(String alleleName, KitEntity kitEntity, LocusEntity locusEntity) {
		if (kitEntity.getLoci().contains(locusEntity)) {
			throw new LocusAlreadyExistException(format(ALLELE_ALREADY_EXIST, alleleName));
		}
	}

	private void assertKitExist(long kitId, KitEntity kitEntity) {
		if (kitEntity == null) {
			throw new KitNotFoundException(format(KIT_NOT_FOUND, kitId));
		}
	}

	private LocusEntity getAlleleEntity(String alleleName) {
		LocusEntity locusEntity = locusRepository.findByName(alleleName);
		if (locusEntity == null) {
			locusEntity = new LocusEntity(alleleName);
		}
		return locusEntity;
	}

	private Locus findLocusById(long kitId, long alleleId, List<LocusEntity> alleleEntities) {
		for (LocusEntity locusEntity : alleleEntities) {
			if (locusEntity.getId() == alleleId) {
				return translateLocus(locusEntity);
			}
		}
		throw new LocusNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
	}

	private List<Locus> translateLoci(List<LocusEntity> alleleEntities) {
		ArrayList<Locus> loci = newArrayList();
		for (LocusEntity locusEntity : alleleEntities) {
			loci.add(translateLocus(locusEntity));
		}
		return loci;
	}

	private Locus translateLocus(LocusEntity locusEntity) {
		Locus locus = new Locus();
		locus.setLocusId(locusEntity.getId());
		locus.setName(locusEntity.getName());
		return locus;
	}
}
