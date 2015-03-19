package me.zeph.relations.service;

import me.zeph.relations.model.api.Allele;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class AlleleService {
	public List<Allele> getAlleles(long kitId) {
		return newArrayList();
	}
}
