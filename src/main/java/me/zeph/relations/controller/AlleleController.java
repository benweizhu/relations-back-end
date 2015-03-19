package me.zeph.relations.controller;

import me.zeph.relations.model.api.Allele;
import me.zeph.relations.service.AlleleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "kits/{kitId}/alleles")
public class AlleleController {

	@Autowired
	private AlleleService alleleService;

	@RequestMapping
	public List<Allele> getAlleles(@PathVariable long kitId) {
		return alleleService.getAlleles(kitId);
	}
}
