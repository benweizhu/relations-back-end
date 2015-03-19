package me.zeph.relations.service;

import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class AlleleService {

	@Autowired
	private KitRepository kitRepository;

	public List<Allele> getAlleles(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		if (kitEntity == null) {
			throw new KitNotFoundException("Kit " + kitId + " not found");
		} else {
			return translateAlleles(kitEntity.getAlleles());
		}
	}

	public Allele getAllele(long kitId, long alleleId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		if (kitEntity == null) {
			throw new KitNotFoundException("Kit " + kitId + " not found");
		} else {
			return findAlleleById(kitId, alleleId, kitEntity);
		}
	}

	private Allele findAlleleById(long kitId, long alleleId, KitEntity kitEntity) {
		List<AlleleEntity> alleleEntities = kitEntity.getAlleles();
		for (AlleleEntity alleleEntity : alleleEntities) {
			if (alleleEntity.getId() == alleleId) {
				return translateAllele(alleleEntity);
			}
		}
		throw new AlleleNotFoundException("Allele " + alleleId + " not found in Kit " + kitId);
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
