package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Locus;
import me.zeph.relations.service.LocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(value = "Locus", position = 1)
public class LocusController {

	@Autowired
	private LocusService locusService;

	@ApiOperation(value = "Get Loci by Kit Id")
	@ResponseStatus(value = OK)
	@RequestMapping(value = "/kits/{kitId}/loci", method = GET, produces = APPLICATION_JSON_VALUE)
	public List<Locus> getLoci(@PathVariable long kitId) {
		List<Locus> loci = locusService.getLoci(kitId);
		for (Locus locus : loci) {
			locus.add(selfLink(kitId, locus.getLocusId()));
		}
		return loci;
	}

	@ApiOperation(value = "Get Locus by Kit Id and Locus Id")
	@ResponseStatus(value = OK)
	@RequestMapping(value = "/kits/{kitId}/loci/{locusId}", method = GET, produces = APPLICATION_JSON_VALUE)
	public Locus getLocus(@PathVariable long kitId, @PathVariable long locusId) {
		Locus locus = locusService.getLocus(kitId, locusId);
		locus.add(selfLink(kitId, locusId));
		return locus;
	}

	@ApiOperation(value = "Save Locus by Name")
	@ResponseStatus(value = CREATED)
	@RequestMapping(value = "/kits/{kitId}/loci", method = POST)
	public ResponseEntity<?> addAllele(UriComponentsBuilder uriComponentsBuilder,
	                                   @PathVariable long kitId, @RequestBody Locus requestLocus) {
		locusService.addLocus(kitId, requestLocus.getName());
		return getResponseEntity(uriComponentsBuilder, "/kits/{kitId}/loci", kitId, CREATED);
	}

	@ApiOperation(value = "Delete Locus by Locus Id")
	@ResponseStatus(value = NO_CONTENT)
	@RequestMapping(value = "/kits/{kitId}/loci/{locusId}", method = DELETE)
	public ResponseEntity<?> removeAllele(UriComponentsBuilder uriComponentsBuilder,
	                                      @PathVariable long kitId, @PathVariable long locusId) {
		locusService.removeLocus(kitId, locusId);
		return getResponseEntity(uriComponentsBuilder, "/kits/{kitId}/loci", kitId, NO_CONTENT);
	}

	private ResponseEntity<?> getResponseEntity(UriComponentsBuilder uriComponentsBuilder, String path,
	                                            long urlVariable, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path(path).buildAndExpand(urlVariable).toUri());
		return new ResponseEntity<Void>(headers, httpStatus);
	}

	private Link selfLink(long kitId, long locusId) {
		return linkTo(methodOn(LocusController.class).getLocus(kitId, locusId)).withSelfRel();
	}
}
