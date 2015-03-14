package me.zeph.relations.repository;

import me.zeph.relations.model.entity.KitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends JpaRepository<KitEntity, Long> {

}
