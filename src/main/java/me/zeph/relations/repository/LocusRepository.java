package me.zeph.relations.repository;

import me.zeph.relations.model.entity.LocusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocusRepository extends JpaRepository<LocusEntity, Long> {
	LocusEntity findByName(String name);
}
