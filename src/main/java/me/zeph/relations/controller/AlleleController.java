package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Allele;
import me.zeph.relations.service.AlleleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api(value = "Alleles", position = 1)
public class AlleleController {

	@Autowired
	private AlleleService alleleService;

	@ApiOperation(value = "Get Alleles by Kit Id")
	@ResponseStatus(OK)
	@RequestMapping(value = "kits/{kitId}/alleles", method = GET, produces = APPLICATION_JSON_VALUE)
	public List<Allele> getAlleles(@PathVariable long kitId) {
		return alleleService.getAlleles(kitId);
	}
}
