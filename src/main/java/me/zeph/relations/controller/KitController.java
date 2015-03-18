package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.Kit;
import me.zeph.relations.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api(value = "Kit", position = 0)
@RequestMapping(value = "/kits", method = GET)
public class KitController {

	@Autowired
	private KitService kitService;

	@ApiOperation(value = "Get Kits")
	@RequestMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public List<Kit> getKits() {
		return kitService.getKits();
	}
}
