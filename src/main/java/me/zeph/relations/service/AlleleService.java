package me.zeph.relations.service;

import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.AlleleEntity;
import me.zeph.relations.repository.AlleleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class AlleleService {

	@Autowired
	private AlleleRepository alleleRepository;

	public List<Allele> getAlleles(long kitId) {
		List<AlleleEntity> alleleEntities = alleleRepository.findAll();
		List<Allele> alleles = translateAlleles(alleleEntities);
		return alleles;
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
