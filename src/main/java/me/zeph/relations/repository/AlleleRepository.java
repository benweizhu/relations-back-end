package me.zeph.relations.repository;

import me.zeph.relations.model.entity.AlleleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlleleRepository extends JpaRepository<AlleleEntity, Long> {
	AlleleEntity findByName(String name);
}
