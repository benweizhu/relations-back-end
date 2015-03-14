package me.zeph.relations.model;

import com.wordnik.swagger.annotations.ApiModel;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@ApiModel
public class Kit {
	private List<String> kits;

	public List<String> getKits() {
		return kits;
	}

	public void setKits(List<String> kits) {
		this.kits = kits;
	}

	public void addKit(String[] kits) {
		this.kits = newArrayList(kits);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		Kit kit = (Kit) o;

		return kits.equals(kit.kits);

	}

	@Override
	public int hashCode() {
		return kits.hashCode();
	}
}
