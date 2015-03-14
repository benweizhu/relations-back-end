package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.exception.KitNotExistException;
import me.zeph.relations.model.api.LocusCode;
import me.zeph.relations.service.LocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api(value = "Loci", position = 1)
@RequestMapping(value = "/loci", method = GET)
public class LocusController {

	@Autowired
	public LocusService locusService;

	@ApiOperation(value = "Get Loci by Kit")
	@RequestMapping(value = "/{kit}", produces = {APPLICATION_JSON_VALUE})
	@ResponseStatus(value = OK)
	public LocusCode getLocusCode(@PathVariable String kit) throws KitNotExistException {
		return locusService.getLocusCode(kit);
	}
}
