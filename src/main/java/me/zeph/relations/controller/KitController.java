package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
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
		List<Kit> kits = kitService.getKits();
		for (Kit kit : kits) {
			kit.add(linkTo(methodOn(KitController.class).getKit(kit.getKitId())).withSelfRel());
		}
		return kits;
	}

	@ApiOperation(value = "Get Kit by Id")
	@RequestMapping(value = "/{kitId}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public Kit getKit(@PathVariable long kitId) {
		Kit kit = kitService.getKit(kitId);
		kit.add(linkTo(methodOn(KitController.class).getKit(kitId)).withSelfRel());
		return kit;
	}
}
