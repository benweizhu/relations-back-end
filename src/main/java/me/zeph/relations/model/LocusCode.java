package me.zeph.relations.model;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

public class LocusCode {

	private List<String> codes;

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void addCodes(Set<String> codes) {
		this.codes = newArrayList(codes);
	}

}
