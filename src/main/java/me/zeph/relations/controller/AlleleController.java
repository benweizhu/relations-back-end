package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.service.AlleleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
@Api(value = "Alleles", position = 1)
public class AlleleController {

	@Autowired
	private AlleleService alleleService;

	@ApiOperation(value = "Get Alleles by Kit Id")
	@ResponseStatus(OK)
	@RequestMapping(value = "/kits/{kitId}/alleles", method = GET, produces = APPLICATION_JSON_VALUE)
	public List<Allele> getAlleles(@PathVariable long kitId) {
		List<Allele> alleles = alleleService.getAlleles(kitId);
		for (Allele allele : alleles) {
			allele.add(selfLink(kitId, allele.getAlleleId()));
		}
		return alleles;
	}

	@ApiOperation(value = "Get Allele by Kit Id and Allele Id")
	@ResponseStatus(OK)
	@RequestMapping(value = "/kits/{kitId}/alleles/{alleleId}", method = GET, produces = APPLICATION_JSON_VALUE)
	public Allele getAllele(@PathVariable long kitId, @PathVariable long alleleId) {
		Allele allele = alleleService.getAllele(kitId, alleleId);
		allele.add(selfLink(kitId, alleleId));
		return allele;
	}

	@ApiOperation(value = "Save Allele by Name")
	@ResponseStatus(value = CREATED)
	@RequestMapping(value = "/kits/{kitId}/alleles", method = POST)
	public ResponseEntity<?> createCustomer(UriComponentsBuilder uriComponentsBuilder,
	                                        @PathVariable long kitId, @RequestBody Allele requestAllele) {
		alleleService.addAllele(kitId, requestAllele.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/kits/{kitId}/alleles").buildAndExpand(kitId).toUri());
		return new ResponseEntity<Void>(headers, CREATED);
	}


	private Link selfLink(long kitId, long alleleId) {
		return linkTo(methodOn(AlleleController.class).getAllele(kitId, alleleId)).withSelfRel();
	}
}
