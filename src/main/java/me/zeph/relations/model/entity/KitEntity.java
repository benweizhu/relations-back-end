package me.zeph.relations.model.entity;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Entity
@Table(name = "RELT_KIT")
public class KitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_KIT_GEN")
	@SequenceGenerator(name = "RELT_KIT_GEN")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELR_KIT_ALLELE", joinColumns = {@JoinColumn(name = "kit_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "allele_id", referencedColumnName = "id")})
	private List<LocusEntity> alleles = newArrayList();

	public KitEntity() {
	}

	public KitEntity(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<LocusEntity> getAlleles() {
		return alleles;
	}

	public void addAllele(LocusEntity allele) {
		if (!alleles.contains(allele)) {
			alleles.add(allele);
		}
		if (!allele.getKits().contains(this)) {
			allele.getKits().add(this);
		}
	}

	public void removeAllele(LocusEntity allele) {
		if (alleles.contains(allele)) {
			alleles.remove(allele);
		}
		if (allele.getKits().contains(this)) {
			allele.getKits().remove(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		KitEntity kitEntity = (KitEntity) o;
		return (name != null ? !name.equals(kitEntity.name) : kitEntity.name != null) ? false : true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
