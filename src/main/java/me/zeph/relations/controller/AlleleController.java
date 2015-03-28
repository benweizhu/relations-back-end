package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.model.entity.LocusEntity;
import me.zeph.relations.service.AlleleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(value = "Allele", position = 4)
public class AlleleController {

	@Autowired
	private AlleleService alleleService;

	@ApiOperation(value = "Get Alleles by locusId")
	@RequestMapping(value = "/loci/{locusId}/alleles", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public List<Allele> getAlleles(@PathVariable long locusId) {
		List<Allele> alleles = alleleService.getAlleles(locusId);
		for (Allele allele : alleles) {
			selfLink(locusId, allele, allele.getAlleleValue());
		}
		return alleles;
	}

	@ApiOperation(value = "Get Allele by locusId and alleleValue")
	@RequestMapping(value = "/loci/{locusId}/alleles/{alleleValue}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public Allele getAllele(@PathVariable long locusId, @PathVariable double alleleValue) {
		Allele allele = alleleService.getAllele(locusId, alleleValue);
		selfLink(locusId, allele, alleleValue);
		return allele;
	}

	@ApiOperation(value = "Save Allele by Name and Probability")
	@RequestMapping(value = "/loci/{locusId}/alleles", method = POST)
	@ResponseStatus(value = CREATED)
	public ResponseEntity<?> addAllele(UriComponentsBuilder uriComponentsBuilder, @PathVariable long locusId,
	                                   @RequestBody Allele requestAllele) {
		LocusEntity locusEntity = alleleService.addAllele(locusId, requestAllele);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/loci/{locusId}/alleles")
				.buildAndExpand(locusEntity.getId()).toUri());
		return new ResponseEntity<Void>(headers, CREATED);
	}

	private void selfLink(long locusId, Allele allele, double alleleValue) {
		allele.add(linkTo(methodOn(AlleleController.class).getAllele(locusId, alleleValue)).withSelfRel());
	}
}
