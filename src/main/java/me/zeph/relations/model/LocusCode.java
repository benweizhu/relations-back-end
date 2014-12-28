package me.zeph.relations.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class LocusCode {

	private List<String> codes;

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void addCodes(String[] codes) {
		this.codes = newArrayList(codes);
	}

}
