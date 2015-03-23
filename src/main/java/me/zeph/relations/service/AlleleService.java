package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleAlreadyExistException;
import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.AlleleRepository;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.*;

@Service
public class AlleleService {

	@Autowired
	private KitRepository kitRepository;

	@Autowired
	private AlleleRepository alleleRepository;

	public List<Allele> getAlleles(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return translateAlleles(kitEntity.getAlleles());
	}

	public Allele getAllele(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		return findAlleleById(kitId, alleleId, kitEntity);
	}

	public void addAllele(long kitId, String alleleName) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		addAlleleToKit(alleleName, kitEntity);

		kitRepository.saveAndFlush(kitEntity);
	}

	public void removeAllele(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		assertKitExist(kitId, kitEntity);
		removeAlleleFromKit(kitId, alleleId, kitEntity);

		kitRepository.saveAndFlush(kitEntity);
	}

	private void removeAlleleFromKit(long kitId, long alleleId, KitEntity kitEntity) {
		AlleleEntity allele = alleleRepository.findOne(alleleId);
		assertAlleleNotExist(kitId, alleleId, kitEntity, allele);
		kitEntity.removeAllele(allele);
	}

	private void addAlleleToKit(String alleleName, KitEntity kitEntity) {
		AlleleEntity alleleEntity = getAlleleEntity(alleleName);
		assertAlleleAlreadyExist(alleleName, kitEntity, alleleEntity);
		kitEntity.addAllele(alleleEntity);
	}

	private void assertAlleleNotExist(long kitId, long alleleId, KitEntity kitEntity, AlleleEntity allele) {
		if (!kitEntity.getAlleles().contains(allele)) {
			throw new AlleleNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
		}
	}

	private void assertAlleleAlreadyExist(String alleleName, KitEntity kitEntity, AlleleEntity alleleEntity) {
		if (kitEntity.getAlleles().contains(alleleEntity)) {
			throw new AlleleAlreadyExistException(format(ALLELE_ALREADY_EXIST, alleleName));
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

	private Allele findAlleleById(long kitId, long alleleId, KitEntity kitEntity) {
		List<AlleleEntity> alleleEntities = kitEntity.getAlleles();
		for (AlleleEntity alleleEntity : alleleEntities) {
			if (alleleEntity.getId() == alleleId) {
				return translateAllele(alleleEntity);
			}
		}
		throw new AlleleNotFoundException(format(ALLELE_NOT_FOUND_IN_KIT, alleleId, kitId));
	}

	private List<Allele> translateAlleles(List<AlleleEntity> alleleEntities) {
		ArrayList<Allele> alleles = newArrayList();
		for (AlleleEntity alleleEntity : alleleEntities) {
			alleles.add(translateAllele(alleleEntity));
		}
		return alleles;
	}

	private Allele translateAllele(AlleleEntity alleleEntity) {
		Allele allele = new Allele();
		allele.setAlleleId(alleleEntity.getId());
		allele.setName(alleleEntity.getName());
		return allele;
	}
}
