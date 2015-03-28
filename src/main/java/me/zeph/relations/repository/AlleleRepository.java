package me.zeph.relations.repository;

import me.zeph.relations.model.entity.AlleleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlleleRepository extends JpaRepository<AlleleEntity, Long> {
	AlleleEntity findByLocusIdAndAllele(long locusId, double allele);

	List<AlleleEntity> findByLocusId(long locusId);
}
