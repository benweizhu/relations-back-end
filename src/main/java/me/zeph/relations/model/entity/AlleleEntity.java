package me.zeph.relations.model.entity;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Entity
@Table(name = "RELT_ALLELE")
public class AlleleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_ALLELE_GEN")
	@SequenceGenerator(name = "RELT_ALLELE_GEN")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(mappedBy = "alleles")
	private List<KitEntity> kits = newArrayList();

	public AlleleEntity() {
	}

	public AlleleEntity(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<KitEntity> getKits() {
		return kits;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AlleleEntity that = (AlleleEntity) o;
		return (name != null ? !name.equals(that.name) : that.name != null) ? false : true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
