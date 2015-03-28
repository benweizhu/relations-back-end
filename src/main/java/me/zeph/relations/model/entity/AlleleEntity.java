package me.zeph.relations.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "RELT_ALLELE_PROBABILITY")
public class AlleleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_ALLELE_PROBABILITY_GEN")
	@SequenceGenerator(name = "RELT_ALLELE_PROBABILITY_GEN")
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "LOCUS_ID")
	private LocusEntity locus = new LocusEntity();

	@Column(name = "ALLELE")
	private double allele;

	@Column(name = "PROBABILITY")
	private double probability;

	public double getAllele() {
		return allele;
	}

	public void setAllele(double allele) {
		this.allele = allele;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getProbability() {
		return probability;
	}

	public LocusEntity getLocus() {
		return locus;
	}

	public void setLocus(LocusEntity locus) {
		this.locus = locus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AlleleEntity)) return false;

		AlleleEntity that = (AlleleEntity) o;

		if (Double.compare(that.allele, allele) != 0) return false;
		if (!locus.equals(that.locus)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = locus.hashCode();
		temp = Double.doubleToLongBits(allele);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
